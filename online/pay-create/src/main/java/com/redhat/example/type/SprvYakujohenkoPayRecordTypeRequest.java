package com.redhat.example.type;

import lombok.Data;

// Business Object
import com.redhat.example.type.SprvYakujoHenkoTypeRequest;
import com.redhat.example.entity.CustomerEntity;
import com.redhat.example.entity.PayRecordEntity;

@Data
public class SprvYakujohenkoPayRecordTypeRequest {
    private SprvYakujoHenkoTypeRequest request;
    private CustomerEntity customer;
    private PayRecordEntity payrecord;
}
