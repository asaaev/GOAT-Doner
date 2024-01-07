package com.exampleproject.GOATDONER.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class DonerOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private Date placedAt;

    @NotBlank(message = "Delivery name is required")
    private String DeliveryName;
    @NotBlank(message = "Delivery street is required")
    private String DeliveryStreet;
    @NotBlank(message = "Delivery city is required")
    private String DeliveryCity;
    @NotBlank(message = "Delivery state ir required")
    private String DeliveryState;
    @NotBlank(message = "Zip code is required")
    private String DeliveryZip;
//    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Doner> donerList = new ArrayList<>();

    public void addDoner (Doner doner){
        this.donerList.add(doner);
    }
}
