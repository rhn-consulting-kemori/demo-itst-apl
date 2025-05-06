package com.redhat.example.type;

import lombok.Data;

// Business Object
import com.redhat.example.type.PayCreateTypeRequest;
import com.redhat.example.entity.CustomerEntity;
import com.redhat.example.entity.PayRecordEntity;

@Data
public class PayRecordCreateTypeRequest {
    PayCreateTypeRequest request;
    CustomerEntity customer;
    PayRecordEntity payrecord;
    PayRecordEntity payrecord_zengetsu;
}
