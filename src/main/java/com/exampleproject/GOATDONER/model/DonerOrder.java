package com.exampleproject.GOATDONER.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DonerOrder {
    private String DeliveryName;
    private String DeliveryStreet;
    private String DeliveryCity;
    private String DeliveryState;
    private String DeliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Doner> donerList = new ArrayList<>();

    public void addDoner (Doner doner){
        this.donerList.add(doner);
    }
}
