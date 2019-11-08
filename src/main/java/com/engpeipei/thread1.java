package com.engpeipei;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class thread1 extends Thread {

    Object urlL;

    JSONObject[] myresponse;

    private List<String> arrayURL = new ArrayList<String>();

    public thread1(Object urlL) {
        this.urlL = urlL;
    }

    public void run() {
        arrayURL = urlL.getArrayURL();
        myresponse=new JSONObject[arrayURL.size()];
        int i=0;
        for (String link : arrayURL) {
            //System.out.println(link);
            try {
                String Linkurl=link.concat("?client_id=0a7e1f9cbc12c80138fe&client_secret=c4de03d8e58af0363a71c3efbeb6e7a695122de1 ");

                URL obj = new URL(Linkurl);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                int responseCode = con.getResponseCode();
                //System.out.println("\nSending 'GET' request to URL"+Linkurl);
                //System.out.println("Response Code :" +responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //System.out.println(response.toString());
                myresponse[i] = new JSONObject(response.toString());
                i++;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public JSONObject[] getMyresponse(){return myresponse;}
}