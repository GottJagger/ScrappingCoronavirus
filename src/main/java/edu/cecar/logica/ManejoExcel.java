/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.logica;

import static edu.cecar.logica.ScrappingPDF.FormateoDeArrayList;
import static edu.cecar.logica.ScrappingPDF.GuardarDatosPorColumnaPDF;
import static edu.cecar.logica.ScrappingPDF.guardarDatosPDF;
import static edu.cecar.logica.ScrappingPDF.lectorPDF;
import edu.cecar.persistencia.CasosCovid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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

        HSSFWorkbook ArchivoExcel = new HSSFWorkbook();

        for (int i = 0; i < 10; i++) {
            Sheet hoja = ArchivoExcel.createSheet(PDF1.get(i).getPais());
            
            
        }
        
        FileOutputStream salida = new FileOutputStream("./excel/CasosCovid.xlsm");
        ArchivoExcel.write(salida);
        ArchivoExcel.close();
        salida.close();
    }
    
    public static void main(String[] args) throws IOException {
        generarExcel();
    }
}
