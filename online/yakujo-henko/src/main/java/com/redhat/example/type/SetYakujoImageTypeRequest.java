package com.redhat.example.type;

import lombok.Data;

// BUsiness Object
import com.redhat.example.type.YakujoHenkoTypeRequest;
import com.redhat.example.entity.CustomerEntity;

@Data
public class SetYakujoImageTypeRequest {
    private YakujoHenkoTypeRequest request;
    private CustomerEntity customer;
}
