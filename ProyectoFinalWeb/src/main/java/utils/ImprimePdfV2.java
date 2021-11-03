/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import static utils.ImprimePdf.AbrePdf;

/**
 *
 * @author mack
 */
public class ImprimePdfV2 {


    /**
     * Este metodo genera un archivo pdf
     * @param imp   array de String que contiene las lineas que se van a imprimir
     * @param cNombre  nombre del documento que se concatenara a un numero aleatorio para que no halla archivos duplicados
     */
    public static void imprime(String[][] encabezado,String[][] detalle,String cTitulo ,String cNombre ){
        
        String destino = "";
        Font negrita = new Font(Font.FontFamily.COURIER,12,Font.BOLD, BaseColor.BLACK);   
        Font normal = new Font(Font.FontFamily.COURIER,10,Font.NORMAL, BaseColor.BLACK);   
        Font italica = new Font(Font.FontFamily.COURIER,10,Font.ITALIC, BaseColor.BLACK);   
        Chunk nuevaLinea = Chunk.NEWLINE;
        
        int fila    = detalle.length;
        int columna = detalle[0].length;

        
        try {
            
            Document docu = new Document();

            int numero = (int)(Math.random()*1000000+1);

            // nombre del reporte
            destino = cNombre+numero+".pdf";

            System.out.println("nombre: "+destino);
            
            PdfWriter.getInstance(docu,new FileOutputStream(destino));
            docu.open();

            Paragraph lineaBlanco;
            Paragraph p;
            
            // insertamos el titulo
            p = new Paragraph(cTitulo,new Font(Font.FontFamily.COURIER, 20, Font.BOLD, BaseColor.BLACK ) );
            p.setAlignment(Element.ALIGN_CENTER);
            
            docu.add(p);

            lineaBlanco = new Paragraph("\n",new Font(Font.FontFamily.COURIER, 12, Font.BOLD, BaseColor.BLACK ) );
            
            docu.add(lineaBlanco);
            
            // insertamos los encabezados 
            PdfPTable tabla = new PdfPTable(columna);    
            tabla.setWidthPercentage(100);
            PdfPCell celda;
            
            for(int i=0;i<columna;i++){
                celda = new PdfPCell(new Phrase(encabezado[0][i],negrita));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tabla.addCell(celda);
            }
            

            // insertamos los detalles 
            for(int i=0;i<fila;i++){
            
                for(int j=0;j<columna;j++){
                
                    // detalle[i][j] = "DETALLE ["+String.valueOf(i)+"]]"+String.valueOf(j)+"]";
                
                    celda = new PdfPCell(new Phrase(detalle[i][j],italica));
                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda.setBackgroundColor(BaseColor.WHITE);
                    tabla.addCell(celda);
                    
                }
            
            }

            docu.add(tabla);
            
            
            docu.close();

            // System.out.println("PDF Creado Exitosamente!!!");

            //MiMensaje.MuestraMensaje("Atencion","El archivo PDF se creo exitosamente");

            AbrePdf(destino);

        } catch(DocumentException | FileNotFoundException e){

            System.out.println("Error: "+e.getMessage());

            //MiMensaje.MuestraMensaje("Error: ",e.getMessage());

        }

    }

    
     /**
     * Este metodo abre el archivo pdf en una ventana
     * @param archivo   nombre del archivo que se debe abrir
     */
    public static void AbrePdf(String archivo){

        try {

            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);

        }catch (IOException ex) {

            System.out.println(ex);

        }

    }

    static void imprime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
