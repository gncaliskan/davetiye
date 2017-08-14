package com.example.gncal.davetiye.Business;

import android.os.AsyncTask;

import com.example.gncal.davetiye.Model.Invitee;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gncal on 8/4/2017.
 */

public class SendSMS extends AsyncTask<List<Invitee>, Void, String> {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected String doInBackground(List<Invitee>... params) {
        String response = null;
        for (Invitee invitee : params[0]) {
            try {
                response += run("https://api.iletimerkezi.com/v1/send-sms/get/?username=5333665570&password=azrazra&text=deneme&receipents="+formatPhoneNumber(invitee.getPhoneNumber())+"&sender=ILETI%20MRKZI");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(response);
        }

        return response;

    }


    private String formatPhoneNumber(String phoneNumber){
        return phoneNumber.replaceAll("[^\\d]", "");

    }

}