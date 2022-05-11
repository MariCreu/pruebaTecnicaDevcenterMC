package com.devcenter.mc.service.impl;

import com.devcenter.mc.model.Coche;
import com.devcenter.mc.model.Precio;
import com.devcenter.mc.model.dto.CocheDTO;
import com.devcenter.mc.repository.CocheDAO;
import com.devcenter.mc.repository.PrecioDAO;
import com.devcenter.mc.service.CocheService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CocheServiceImpl implements CocheService {

    @Autowired
    private CocheDAO cocheDao;

    @Autowired
    private PrecioDAO precioDAO;


    private static final String MARCA = "marca eq";
    private static final String MODELO = "modelo eq";
    private static final String COLOR = "color eq";
    private static final String PRECIO_MENOR = "precio lt";
    private static final String PRECIO_MAYOR = "precio gt";


    @Override
    public List<CocheDTO> findAll() {
        Iterable<Precio> precios = precioDAO.findAll();
        return mapToCocheDTO(precios);
    }

    @Override
    public List<CocheDTO> getByFilter(String filter) {
        List<CocheDTO> listaCochesDTO = null;
        if (filter.contains(MARCA)) {
            String paramMarca = StringUtils.trimWhitespace((filter.substring(MARCA.length()))).toLowerCase();
            Iterable<Precio> precios = precioDAO.findPreciosByCoche_MarcaNombreMARCA(paramMarca);
            listaCochesDTO = mapToCocheDTO(precios);

        } else if (filter.contains(MODELO)) {
            String paramModelo = StringUtils.trimWhitespace((filter.substring(MODELO.length()))).toLowerCase();
            Iterable<Precio> precios = precioDAO.findPreciosByCoche_NombreModelo(paramModelo);
            listaCochesDTO = mapToCocheDTO(precios);
        } else if (filter.contains(COLOR)) {
            String paramColor = StringUtils.trimWhitespace((filter.substring(COLOR.length()))).toLowerCase();
            Iterable<Precio> precios = precioDAO.findPreciosByCoche_Color(paramColor);
            listaCochesDTO = mapToCocheDTO(precios);
        } else if (filter.contains(PRECIO_MENOR)) {
            String paramPrecioMenor = StringUtils.trimWhitespace((filter.substring(PRECIO_MENOR.length()))).toLowerCase();
            Iterable<Precio> precios = precioDAO.findPreciosByPrecioLessThanEqual(Integer.valueOf(paramPrecioMenor));
            listaCochesDTO = mapToCocheDTO(precios);
        } else if (filter.contains(PRECIO_MAYOR)) {
            String paramPrecioMayor = StringUtils.trimWhitespace((filter.substring(PRECIO_MAYOR.length()))).toLowerCase();
            Iterable<Precio> precios = precioDAO.findPreciosByPrecioGreaterThanEqual(Integer.valueOf(paramPrecioMayor));
            listaCochesDTO = mapToCocheDTO(precios);
        }

        return listaCochesDTO;
    }

    @Override
    public ByteArrayInputStream exportToExcel() throws IOException {

        List<String> columnas = Arrays.asList("ID", "ID_MARCA", "NOMBRE_MODELO", "COLOR");
        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Sheet sheet = workbook.createSheet("Coches");

        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);

        Row row = sheet.createRow(0);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle.setFillPattern(FillPatternType.FINE_DOTS);

        for (int i = 0; i < columnas.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnas.get(i));
            cell.setCellStyle(cellStyle);
        }

        int primeraLinea = 1;
        List<Coche> coches = cocheDao.findAll();
        for (Coche coche : coches) {
            row = sheet.createRow(primeraLinea);
            row.createCell(0).setCellValue(coche.getId());
            row.createCell(1).setCellValue(coche.getMarca().getId());
            row.createCell(2).setCellValue(coche.getNombreModelo());
            row.createCell(3).setCellValue(coche.getColor());
            primeraLinea++;
        }
        workbook.write(stream);
        workbook.close();

        return new ByteArrayInputStream(stream.toByteArray());
    }


    private List<CocheDTO> mapToCocheDTO(Iterable<Precio> precios) {
        List<CocheDTO> listaCochesDTO = new ArrayList<>();
        precios.forEach(precio -> listaCochesDTO.add(
                CocheDTO.builder()
                        .marca(precio.getCoche().getMarca().getNombreMARCA())
                        .nombreModelo(precio.getCoche().getNombreModelo())
                        .color(precio.getCoche().getColor())
                        .precio(precio.getPrecio())
                        .build()
        ));
        return listaCochesDTO;
    }

}
