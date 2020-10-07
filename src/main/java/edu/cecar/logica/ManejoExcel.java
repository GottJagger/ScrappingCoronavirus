/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.logica;

import static edu.cecar.logica.ScrappingPDF.GuardarDatosPorColumnaPDF;
import static edu.cecar.logica.ScrappingPDF.guardarDatosPDF;
import static edu.cecar.logica.ScrappingPDF.lectorPDF;
import edu.cecar.persistencia.CasosCovid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author oderb
 */
public class ManejoExcel {

    public static void generarExcel() throws IOException {

        String col1 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 0, 120, 200, 220, 6);
        String col2 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 180, 120, 100, 220, 6);
        String col3 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 300, 120, 80, 220, 6);
        String col4 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 400, 120, 80, 220, 6);
        String col5 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 450, 120, 80, 220, 6);

        ArrayList<CasosCovid> PDF1 = ScrappingPDF.guardarDatosPDF(lectorPDF("./anexos/20200812-covid-19-sitrep-205.pdf", 0, 120, 500, 200, 9));
        ArrayList<CasosCovid> PDF2 = ScrappingPDF.guardarDatosPDF(lectorPDF("./anexos/20200813-covid-19-sitrep-206.pdf", 0, 120, 500, 200, 6));
        ArrayList<CasosCovid> PDF3 = ScrappingPDF.FormateoDeArrayList(guardarDatosPDF(lectorPDF("./anexos/20200814-covid-19-sitrep-207.pdf", 0, 130, 500, 200, 8)));
        ArrayList<CasosCovid> PDF4 = ScrappingPDF.FormateoDeArrayList(guardarDatosPDF(lectorPDF("./anexos/20200815-covid-19-sitrep-208.pdf", 0, 120, 500, 220, 6)));
        ArrayList<CasosCovid> PDF5 = ScrappingPDF.FormateoDeArrayList(GuardarDatosPorColumnaPDF(col1, col2, col3, col4, col5));

        File file = new File("./excel/plantilla.xlsx");

        FileInputStream archivo = new FileInputStream(file);

        Workbook ArchivoExcel = WorkbookFactory.create(archivo);

        //-------Apertura del archivo---------------------------------------
        for (int i = 0; i < 10; i++) {

            Cell celda;
            Row fila;

            Sheet hoja = ArchivoExcel.getSheetAt(i);

            for (int j = 0; j <= 5; j++) {

                

                fila = hoja.createRow(0);
                celda = fila.createCell(0);
                celda.setCellValue("PDF");
                celda = fila.createCell(1);
                celda.setCellValue("Total de casos confirmados");
                celda = fila.createCell(2);
                celda.setCellValue("Total de nuevos de casos confirmados");
                celda = fila.createCell(3);
                celda.setCellValue("Total de muertos");
                celda = fila.createCell(4);
                celda.setCellValue("Total de nuevos muertos");

                if (hoja.getSheetName().equals("Peru")) {

                    
                    if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Colombia")) {
                    if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Chile")) {
                    if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Argentina")) {
                     if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Ecuador")) {
                     if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Ecuador")) {
                    if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Canada")) {
                     if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Bolivia (Plurinational State of")) {
                    if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Dominican Republic")) {
                     if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Panama")) {
                     if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }

                }
                if (hoja.getSheetName().equals("Guatemala")) {
                     if (j == 1) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(12);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF1.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF1.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF1.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF1.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 2) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(13);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF2.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF2.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF2.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF2.get(i).getTotalNuevosMuertos());

                    }
                    if (j == 3) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(14);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF3.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF3.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF3.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF3.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 4) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(15);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF4.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF4.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF4.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF4.get(i).getTotalNuevosMuertos());
                    }
                    if (j == 5) {
                        fila = hoja.createRow(j);
                        celda = fila.createCell(0);
                        celda.setCellValue(16);
                        celda = fila.createCell(1);
                        celda.setCellValue(PDF5.get(i).getTotalCasosConfirmados());
                        celda = fila.createCell(2);
                        celda.setCellValue(PDF5.get(i).getTotalCasosNuevosConfirmados());
                        celda = fila.createCell(3);
                        celda.setCellValue(PDF5.get(i).getTotalMuertos());
                        celda = fila.createCell(4);
                        celda.setCellValue(PDF5.get(i).getTotalNuevosMuertos());
                    }
                }

            }
            //---------clausura del archivo--------------------------------------
            FileOutputStream salida = new FileOutputStream(file);

            ArchivoExcel.write(salida);
            salida.close();

        }

        ArchivoExcel.close();
    }

    public static void main(String[] args) throws IOException {
        generarExcel();
    }
}
