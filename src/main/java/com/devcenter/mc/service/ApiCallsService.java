package com.devcenter.mc.service;

import com.devcenter.mc.model.ServiceEnum;

import javax.servlet.http.HttpServletRequest;


public interface ApiCallsService {

    void saveApiCalls(HttpServletRequest request, ServiceEnum serviceEnum);
}
