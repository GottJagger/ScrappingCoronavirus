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
import java.io.IOException;

/**
 *
 * @author oderb
 */
public class ScrappingPDF {

    //C:\Users\oderb\Documents\NetBeansProjects\ReconocedorRelaciones\anexos
    
    public static String lectorPDF(String ruta) throws IOException {

        PdfReader lector = new PdfReader(ruta);

        Rectangle rect = new Rectangle(0, 120, 500, 230);

        RenderFilter filtro = new RegionTextRenderFilter(rect);

        TextExtractionStrategy strategy;

        strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filtro);

        String contenido = PdfTextExtractor.getTextFromPage(lector, 9, strategy);

        return contenido;

    }

    public static void main(String[] args) throws IOException {
        System.out.println(lectorPDF("./anexos/20200812-covid-19-sitrep-205.pdf"));
        
    }

}
