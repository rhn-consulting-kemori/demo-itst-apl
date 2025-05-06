# -*- coding: utf-8 -*-
from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
import os

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = \
    'postgresql://{user}:{password}@{host}:{port}/{dbName}'.format(
        user = os.getenv('DB_USER', 'postgres'),
        password = os.getenv('DB_PASS', 'postgres'),
        host = os.getenv('DB_HOST', '127.0.0.1'),
        port = os.getenv('DB_PORT', '5432'),
        dbName = os.getenv('DB_NAME', 'postgres')
    ) # PostgreSQL接続用のURI
db = SQLAlchemy(app)

# Database Mapping
class Customer(db.Model):
    customer_number = db.Column(db.String(), primary_key=True)
    card_number = db.Column(db.String(), nullable=False)
    customer_name = db.Column(db.String(), nullable=False)
    customer_address = db.Column(db.String())
    sprv_limit_amount = db.Column(db.Integer)
    sprv_init_furi_menjo = db.Column(db.String())
    sprv_monthly_payamount = db.Column(db.Integer)
    sprv_rate = db.Column(db.Float)

class Yakujo_henko_history(db.Model):
    history_seq = db.Column(db.Integer, primary_key=True)
    customer_number = db.Column(db.String(), nullable=False)
    change_date = db.Column(db.String(), nullable=False)
    pay_change_seikyu_shimebi = db.Column(db.String(), nullable=False)
    before_sprv_limit_amount = db.Column(db.Integer)
    before_sprv_init_furi_menjo = db.Column(db.String())
    before_sprv_monthly_payamount = db.Column(db.Integer)
    before_sprv_rate = db.Column(db.Float)
    after_sprv_limit_amount = db.Column(db.Integer)
    after_sprv_init_furi_menjo = db.Column(db.String())
    after_sprv_monthly_payamount = db.Column(db.Integer)
    after_sprv_rate = db.Column(db.Float)

class Shopping_entry(db.Model):
    shopping_number = db.Column(db.Integer, primary_key=True)
    customer_number = db.Column(db.String(), nullable=False)
    use_card_number = db.Column(db.String(), nullable=False)
    use_date = db.Column(db.String(), nullable=False)
    shop_name = db.Column(db.String(), nullable=False)
    use_amount = db.Column(db.Integer, nullable=False)
    pay_type = db.Column(db.String(), nullable=False)

class Shopping_record(db.Model):
    shopping_number = db.Column(db.Integer, primary_key=True)
    customer_number = db.Column(db.String(), nullable=False)
    use_card_number = db.Column(db.String(), nullable=False)
    seikyu_shimebi = db.Column(db.String(), nullable=False)
    pay_type = db.Column(db.String(), nullable=False)
    use_date = db.Column(db.String(), nullable=False)
    shop_name = db.Column(db.String(), nullable=False)
    use_amount = db.Column(db.Integer, nullable=False)

class Pay_record(db.Model):
    customer_number = db.Column(db.String(), primary_key=True)
    seikyu_shimebi = db.Column(db.String(), primary_key=True)
    pay_date = db.Column(db.String(), nullable=False)
    pay_amount = db.Column(db.Integer, nullable=False)
    sp1_pay_amount = db.Column(db.Integer, nullable=False)
    sprv_pay_gankin = db.Column(db.Integer, nullable=False)
    sprv_pay_tesuryo = db.Column(db.Integer, nullable=False)
    sprv_togetu_zandaka = db.Column(db.Integer, nullable=False)
    sprv_togetu_kurizan = db.Column(db.Integer, nullable=False)
    sprv_jigetu_kurizan = db.Column(db.Integer, nullable=False)
    sprv_init_furi_menjo = db.Column(db.String())
    sprv_monthly_payamount = db.Column(db.Integer)
    sprv_rate = db.Column(db.Float)

class Pay_fix_inf(db.Model):
    customer_number = db.Column(db.String(), primary_key=True)
    seikyu_shimebi = db.Column(db.String(), primary_key=True)
    pay_date = db.Column(db.String(), nullable=False)
    pay_amount = db.Column(db.Integer, nullable=False)
    sp1_pay_amount = db.Column(db.Integer, nullable=False)
    sprv_pay_gankin = db.Column(db.Integer, nullable=False)
    sprv_pay_tesuryo = db.Column(db.Integer, nullable=False)
    card_number = db.Column(db.String(), nullable=False)
    customer_name = db.Column(db.String(), nullable=False)
    customer_address = db.Column(db.String())
    sprv_limit_amount = db.Column(db.Integer)
    sprv_togetu_zandaka = db.Column(db.Integer, nullable=False)
    sprv_togetu_kurizan = db.Column(db.Integer, nullable=False)
    sprv_jigetu_kurizan = db.Column(db.Integer, nullable=False)
    sprv_init_furi_menjo = db.Column(db.String())
    sprv_monthly_payamount = db.Column(db.Integer)
    sprv_rate = db.Column(db.Float)

# Root
@app.route("/")
def index():
    customers = Customer.query.all()
    return render_template('index.html', customers=customers)

@app.route("/show")
def report():
    if request.args.get('customer_number') is not None:
        # Query Parameter Exists
        cus_num = request.args.get('customer_number')
        customer = Customer.query.get(cus_num)
        yakujo_henko_historys = Yakujo_henko_history.query.order_by(Yakujo_henko_history.history_seq.desc()).filter(Yakujo_henko_history.customer_number == cus_num)
        shopping_entrys = Shopping_entry.query.order_by(Shopping_entry.shopping_number.desc()).filter(Shopping_entry.customer_number == cus_num)
        shopping_records = Shopping_record.query.order_by(Shopping_record.shopping_number.desc()).filter(Shopping_record.customer_number == cus_num)
        pay_records = Pay_record.query.order_by(Pay_record.seikyu_shimebi.desc()).filter(Pay_record.customer_number == cus_num)
        pay_fix_infs = Pay_fix_inf.query.order_by(Pay_fix_inf.seikyu_shimebi.desc()).filter(Pay_fix_inf.customer_number == cus_num)
        return render_template('show.html', customer=customer, yakujo_henko_historys=yakujo_henko_historys, shopping_entrys=shopping_entrys, shopping_records=shopping_records, pay_records=pay_records, pay_fix_infs=pay_fix_infs)
    else:
        # Query Parameter Not Exists
        customers = Customer.query.all()
        return render_template('index.html', customers=customers)

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8288, debug=True)
