package com.textbox.services;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * "A good horse runs even at the shadow of the whip"
 *   - http://www.ibiblio.org/zen/gateless-gate/0.html
 * @author supanbe
 */
public class Sender {
    private static final String endpoint="http://ragno.wolfpac.net/cpportal/dispatcher/send/send.php?php?com";
    private static final String proxy="172.18.6.20";
    private static final String port  = "8080";

    private String src;
    private String dst;
    private String msg;
    URLConnection conn = null;
    HttpURLConnection httpconn = null;
    private String res = null;
    private int code = 0;
    
    public Sender( String src, String dst, String msg ) {
        this.src = src;
        this.dst = dst;
        this.msg = msg;

        URL url = null;
        try {
            StringBuffer urlQuery = new StringBuffer();

            urlQuery.append("src=").append(this.src).
                    append("&dst=").append(this.dst).
                    append("&msg=").append(URLEncoder.encode(this.msg, "UTF-8"));
            
            System.out.println("quer:"+ urlQuery.toString());
            url = new URL( endpoint +"&"+ urlQuery.toString());
            
            Properties systemProperties = System.getProperties();
            systemProperties.setProperty("http.proxyHost",proxy);
            systemProperties.setProperty("http.proxyPort",port);

        }catch( MalformedURLException me ){
            me.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


        try {
            
            
            httpconn = (HttpURLConnection)url.openConnection();
            
            httpconn.setRequestMethod( "GET" );
            httpconn.usingProxy();
            httpconn.setDoOutput(true);
            httpconn.setConnectTimeout(60000);
   
            httpconn.connect();
            
            System.out.print("Request:"+httpconn.getURL().getQuery());
            InputStream is = httpconn.getInputStream();

            String str = httpconn.getResponseMessage();
            System.out.println("Response:"+httpconn.getResponseCode());
            System.out.println("Response:"+httpconn.getResponseMessage());

            StringBuffer strbuf = new StringBuffer();
            byte[] buffer = new byte[1024 * 4];
            try {
                int n = 0;
                while (-1 != (n = is.read(buffer))) {
                    strbuf.append(new String(buffer, 0, n));
                }
                is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            
            System.out.println(strbuf.toString());
            if ( httpconn.getResponseCode() == 200 ){
                if ( strbuf.toString().charAt(1) == 'N' ){
                    res="Un-Sent!";
                    code=-1;
                }
                else {
                    res = "Sent!";
                    code = 0;
                }
            }
            httpconn.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }



    }

    public String getResponse () {
        return res;
    }

    public int getResponseCode () {
        return code;
    }

    
}
