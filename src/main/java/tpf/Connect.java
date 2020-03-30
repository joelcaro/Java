/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpf;

import java.io.File;
import org.apache.http.client.methods.HttpGet;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author Joel Caro G
 */
public class Connect {
    
      public static Logger logger = Logger.getLogger(Connect.class);
    private CloseableHttpClient client;
    private String respuesta;
    private final int nro = 1000;

    public String getJSONFromUrl(String url, int timeOut) {
            
        String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resource"
                + File.separator + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);

        long start = System.currentTimeMillis();
        int milisegundo = timeOut * nro;

        RequestConfig config = RequestConfig.custom().setConnectTimeout(milisegundo).build();

        client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

        HttpGet request = new HttpGet(url);

        try {

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                long elapsedTimeMillis = System.currentTimeMillis() - start;
                respuesta = EntityUtils.toString(entity);
                logger.info("Tiempo en dar respuesta el servidor: " + elapsedTimeMillis + " milisegundos");
            }

        } catch (ClientProtocolException e) {
            System.out.println(e.toString());
            logger.error("Se ha producido el siguiente error: " + e.getMessage());
        } catch (ConnectTimeoutException ct) {
            System.out.println(ct.toString());
            logger.error("Se ha producido el siguiente error: " + ct.getMessage());
        } catch (SocketTimeoutException st) {
            System.out.println(st.toString());
            logger.error("Se ha producido el siguiente error: " + st.getMessage());
        } catch (IOException e) {
            System.out.println(e.toString());
            logger.error("Se ha producido el siguiente error: " + e.getMessage());
        }

        return respuesta;

    }

}
