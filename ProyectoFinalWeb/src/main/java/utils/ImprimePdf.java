package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.awt.Desktop;



/**
 * En esta Clase se genera una documento pdf y se almacena en
 * el directorio de la aplicacion
 * @author: Pedro Luis de Jesus Hernandez, Angel Magdiel Pineda Cruz
 * @version: 2021/08/31/E
 */

public class ImprimePdf {

    public ImprimePdf(){

    }

    /**
     * Este metodo genera un archivo pdf
     * @param imp   array de String que contiene las lineas que se van a imprimir
     * @param cNombre  nombre del documento que se concatenara a un numero aleatorio para que no halla archivos duplicados
     */
    public static void imprime(String[] imp,String cNombre){
        String destino = "";
        Font negrita = new Font(Font.FontFamily.COURIER,12,Font.BOLD, BaseColor.BLACK);   
        Font normal = new Font(Font.FontFamily.COURIER,12,Font.NORMAL, BaseColor.BLACK);   
        Font italica = new Font(Font.FontFamily.COURIER,12,Font.ITALIC, BaseColor.BLACK);   
        Chunk nuevaLinea = Chunk.NEWLINE;
        
        try {
            Document docu = new Document();

            int numero = (int)(Math.random()*1000000+1);

            // destino = cNombre+numero+".pdf";

            destino = cNombre+numero+".pdf";

            PdfWriter.getInstance(docu,new FileOutputStream(destino));
            docu.open();

            Paragraph p2 = new Paragraph(0);
            p2.add("ESTA ES UNA PRUEBA");
            p2.add(" ");
            p2.add("ESTA ES OTRA PRUEBA");
            docu.add(p2);
            
            Paragraph p;
            int nSize = imp.length;

            for(int i = 0; i < nSize;i++ ){
                
                // p = new Paragraph(imp[i],new Font(Font.FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)) );
                
                p = new Paragraph(imp[i],italica);
                docu.add(p);
            }

            docu.close();

            // System.out.println("PDF Creado Exitosamente!!!");

            //MiMensaje.MuestraMensaje("Atencion","El archivo PDF se creo exitosamente");

            AbrePdf(destino);

        } catch(DocumentException | FileNotFoundException e){

            // System.out.println("Error: "+e.getMessage());

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

}
