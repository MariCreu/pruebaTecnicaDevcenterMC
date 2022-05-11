package com.devcenter.mc.service;

import com.devcenter.mc.model.dto.CocheDTO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface CocheService {

    List<CocheDTO> findAll();

    List<CocheDTO> getByFilter(String filter);

    ByteArrayInputStream exportToExcel() throws IOException;
}
