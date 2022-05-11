package com.devcenter.mc.repository;

import com.devcenter.mc.model.ApiCall;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCallsDAO extends MongoRepository<ApiCall, String> {
}
