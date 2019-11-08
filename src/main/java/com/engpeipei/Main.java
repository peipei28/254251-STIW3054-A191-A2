package com.engpeipei;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String url="" ;

        try {
            String Linkurl = "https://api.github.com/users/zhamri/followers?per_page=100&client_id=0a7e1f9cbc12c80138fe&client_secret=c4de03d8e58af0363a71c3efbeb6e7a695122de1";
            URL obj = new URL(Linkurl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL"+Linkurl);
            //System.out.println("Response Code :" +responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine())!=null) {
                response.append(inputLine);
            }
            in.close();
            //System.out.println(response.toString());

            JSONArray myresponse = new JSONArray(response.toString());
            //System.out.println(myresponse);

            Object object = new Object();
            for(int i =0; i<myresponse.length();i++) {
                url = myresponse.getJSONObject(i).getString("url");
                object.setArrayURL(url);
                //System.out.println(url);
            }

            thread1 T1 = new thread1(object);
            T1.run();
            display display=new display(T1.getMyresponse());
            display.display();

        }catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}
