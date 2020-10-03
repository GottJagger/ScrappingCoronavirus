/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.logica;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import edu.cecar.persistencia.CasosCovid;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author oderb
 */
public class ScrappingPDF {

    //C:\Users\oderb\Documents\NetBeansProjects\ReconocedorRelaciones\anexos
    public static String lectorPDF(String ruta, int l, int u, int r, int d, int pagina) throws IOException {

        PdfReader lector = new PdfReader(ruta);

        Rectangle rect = new Rectangle(l, u, r, d);

        RenderFilter filtro = new RegionTextRenderFilter(rect);

        TextExtractionStrategy strategy;

        strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filtro);

        String contenido = PdfTextExtractor.getTextFromPage(lector, pagina, strategy);

        return contenido;

    }

    public static ArrayList<CasosCovid> guardarDatosPDF(String Documento) throws IOException {
        String str[] = Documento.split("\n");

        ArrayList<CasosCovid> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // System.out.println(str[i]);
            CasosCovid cc = new CasosCovid();
            String k = str[i];
            String str1[] = k.split("  ");
            cc.setPais(str1[0]);
            cc.setTotalCasosConfirmados(Integer.parseInt(str1[1].replace(" ", "")));
            cc.setTotalCasosNuevosConfirmados(Integer.parseInt(str1[2].replace(" ", "")));
            cc.setTotalMuertos(Integer.parseInt(str1[3].replace(" ", "")));
            cc.setTotalNuevosMuertos(Integer.parseInt(str1[4].replace(" ", "")));
            list.add(cc);

        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        guardarDatosPDF(lectorPDF("./anexos/20200812-covid-19-sitrep-205.pdf", 0, 120, 500, 200, 9)).forEach(e -> {
            System.out.println("PAIS: " + e.getPais() + "\nTOTAL DE CASOS CONFIRMADOS: " + e.getTotalCasosConfirmados() + "\nTOTAL DE NUEVOS CASOS CONFIRMADOS: " + e.getTotalCasosNuevosConfirmados() + "\nTOTAL DE MUERTOS: " + e.getTotalMuertos() + "\nTOTAL NUEVOS MUERTOS: " + e.getTotalNuevosMuertos()+"\n\n");
        });

//        System.out.println(lectorPDF("./anexos/20200812-covid-19-sitrep-205.pdf", 0, 120, 500, 200,9));
//        System.out.println("----------------------------------------------------------------------");
//        System.out.println(lectorPDF("./anexos/20200813-covid-19-sitrep-206.pdf", 0, 120, 500, 200,6));
//        System.out.println("----------------------------------------------------------------------");
//        System.out.println(lectorPDF("./anexos/20200814-covid-19-sitrep-207.pdf", 0, 130, 500, 200,8));
//        System.out.println("----------------------------------------------------------------------");
//        System.out.println(lectorPDF("./anexos/20200815-covid-19-sitrep-208.pdf", 0, 120, 500, 220,6));
//        System.out.println("----------------------------------------------------------------------");
//        System.out.println(lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 0, 130, 500, 200,6));
    }

}
