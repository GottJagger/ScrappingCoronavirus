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

        if (Documento.equals(lectorPDF("./anexos/20200815-covid-19-sitrep-208.pdf", 0, 120, 500, 220, 6)) || Documento.equals(lectorPDF("./anexos/20200814-covid-19-sitrep-207.pdf", 0, 130, 500, 200, 8))) {
            for (int i = 0; i < 11; i++) {
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

        } else {
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
        }

        return list;
    }

    public static ArrayList<CasosCovid> GuardarDatosPorColumnaPDF(String col1, String col2, String col3, String col4, String col5) {
        String str1[] = col1.split("\n");
        String str2[] = col2.split("\n");
        String str3[] = col3.split("\n");
        String str4[] = col4.split("\n");
        String str5[] = col5.split("\n");

        ArrayList<CasosCovid> list = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            CasosCovid cc = new CasosCovid();
            cc.setPais(str1[i]);
            cc.setTotalCasosConfirmados(Integer.parseInt(str2[i].replace(" ", "")));
            cc.setTotalCasosNuevosConfirmados(Integer.parseInt(str3[i].replace(" ", "")));
            cc.setTotalMuertos(Integer.parseInt(str4[i].replace(" ", "")));
            cc.setTotalNuevosMuertos(Integer.parseInt(str5[i].replace(" ", "")));
            list.add(cc);
        }
        return list;
    }

    public static ArrayList<CasosCovid> FormateoDeArrayList(ArrayList<CasosCovid> list) {
        
        list.remove(1);

        return list;
    }
    
    public static void mostrarEnConsola()throws IOException {
        String col1 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 0, 120, 200, 220, 6);
  
        String col2 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 180, 120, 100, 220, 6);

        String col3 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 300, 120, 80, 220, 6);

        String col4 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 400, 120, 80, 220, 6);

        String col5 = lectorPDF("./anexos/20200816-covid-19-sitrep-209.pdf", 450, 120, 80, 220, 6);


        guardarDatosPDF(lectorPDF("./anexos/20200812-covid-19-sitrep-205.pdf", 0, 120, 500, 200, 9)).forEach(e -> {
            System.out.println("PAIS: " + e.getPais() + "\nTOTAL DE CASOS CONFIRMADOS: " + e.getTotalCasosConfirmados() + "\nTOTAL DE NUEVOS CASOS CONFIRMADOS: " + e.getTotalCasosNuevosConfirmados() + "\nTOTAL DE MUERTOS: " + e.getTotalMuertos() + "\nTOTAL NUEVOS MUERTOS: " + e.getTotalNuevosMuertos() + "\n\n");
        });

        System.out.println("**********************************************************************");

        guardarDatosPDF(lectorPDF("./anexos/20200813-covid-19-sitrep-206.pdf", 0, 120, 500, 200, 6)).forEach(e -> {
            System.out.println("PAIS: " + e.getPais() + "\nTOTAL DE CASOS CONFIRMADOS: " + e.getTotalCasosConfirmados() + "\nTOTAL DE NUEVOS CASOS CONFIRMADOS: " + e.getTotalCasosNuevosConfirmados() + "\nTOTAL DE MUERTOS: " + e.getTotalMuertos() + "\nTOTAL NUEVOS MUERTOS: " + e.getTotalNuevosMuertos() + "\n\n");
        });

        System.out.println("**********************************************************************");

        FormateoDeArrayList(guardarDatosPDF(lectorPDF("./anexos/20200814-covid-19-sitrep-207.pdf", 0, 130, 500, 200, 8))).forEach(e -> {
            System.out.println("PAIS: " + e.getPais() + "\nTOTAL DE CASOS CONFIRMADOS: " + e.getTotalCasosConfirmados() + "\nTOTAL DE NUEVOS CASOS CONFIRMADOS: " + e.getTotalCasosNuevosConfirmados() + "\nTOTAL DE MUERTOS: " + e.getTotalMuertos() + "\nTOTAL NUEVOS MUERTOS: " + e.getTotalNuevosMuertos() + "\n\n");
        });

        System.out.println("**********************************************************************");

        FormateoDeArrayList(guardarDatosPDF(lectorPDF("./anexos/20200815-covid-19-sitrep-208.pdf", 0, 120, 500, 220, 6))).forEach(e -> {
            System.out.println("PAIS: " + e.getPais() + "\nTOTAL DE CASOS CONFIRMADOS: " + e.getTotalCasosConfirmados() + "\nTOTAL DE NUEVOS CASOS CONFIRMADOS: " + e.getTotalCasosNuevosConfirmados() + "\nTOTAL DE MUERTOS: " + e.getTotalMuertos() + "\nTOTAL NUEVOS MUERTOS: " + e.getTotalNuevosMuertos() + "\n\n");
        });

        System.out.println("**********************************************************************");

        FormateoDeArrayList(GuardarDatosPorColumnaPDF(col1, col2, col3, col4, col5)).forEach(e -> {
            System.out.println("PAIS: " + e.getPais() + "\nTOTAL DE CASOS CONFIRMADOS: " + e.getTotalCasosConfirmados() + "\nTOTAL DE NUEVOS CASOS CONFIRMADOS: " + e.getTotalCasosNuevosConfirmados() + "\nTOTAL DE MUERTOS: " + e.getTotalMuertos() + "\nTOTAL NUEVOS MUERTOS: " + e.getTotalNuevosMuertos() + "\n\n");
        });
    }


}
