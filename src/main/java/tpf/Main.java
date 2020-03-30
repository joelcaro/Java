/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpf;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static tpf.Connect.logger;

/**
 *
 * @author Joel Caro G
 */
public class Main {
    
    
    public static void main(String args[]) throws Exception{
     
     Properties propiedades = new Properties();
        InputStream archivo;

        try {

            archivo = new FileInputStream("src/main/java/resource/WebService.properties");
            propiedades.load(archivo);

            Connect conexion = new Connect();
        
            String dbg = propiedades.getProperty("time_out");

            int tiempoConexion = Integer.parseInt(dbg);
            String url = propiedades.getProperty("End.point.url");

            Parser p = new Parser();

            p.parse(conexion.getJSONFromUrl(url, tiempoConexion));

            p.recorrerArray();

            logger.info("Total de provincias: " + p.totalProvincia());
        } catch (IOException e) {
            System.out.println(e.toString());
            logger.error("Se ha producido el siguiente error: " + e.getMessage());
        }

        
   }
    
}
