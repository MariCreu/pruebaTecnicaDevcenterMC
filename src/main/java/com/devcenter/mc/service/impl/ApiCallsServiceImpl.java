package com.devcenter.mc.service.impl;

import com.devcenter.mc.model.ApiCall;
import com.devcenter.mc.model.ServiceEnum;
import com.devcenter.mc.repository.ApiCallsDAO;
import com.devcenter.mc.service.ApiCallsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class ApiCallsServiceImpl implements ApiCallsService {

    @Autowired
    private ApiCallsDAO apiCallsDAO;

    @Override
    public void saveApiCalls(HttpServletRequest request, ServiceEnum serviceEnum) {
        ApiCall apiCall = new ApiCall();
        apiCall.setIp(request.getRemoteAddr());
        apiCall.setFecha(new Date());
        apiCall.setService(serviceEnum);
        apiCallsDAO.save(apiCall);
    }
}
