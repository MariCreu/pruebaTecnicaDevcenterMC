package com.devcenter.mc.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "api_calls")
public class ApiCall {
    private String ip;
    private Date fecha;
    private ServiceEnum service;

}
