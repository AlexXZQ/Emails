package com.example.xzq.emails.app;

import android.app.Application;

import com.example.xzq.emails.bean.MailInfo;

import java.io.InputStream;
import java.util.ArrayList;

import javax.mail.Session;
import javax.mail.Store;

/**
 * Created by Xzq on 2017/3/26.
 */

public class MyApplication extends Application{

    private static Store store;
    private ArrayList<InputStream> attachmentsInputStreams;

    public static MailInfo info = new MailInfo();

    public static Session session = null;
    public static Store getStore(){
        return store;
    }

    public static void setStore(Store store){
        MyApplication.store = store;
    }

    public ArrayList<InputStream> getAttachmentsInputStreams() {
        return attachmentsInputStreams;
    }

    public void setAttachmentsInputStreams(ArrayList<InputStream> attachmentsInputStreams) {
        this.attachmentsInputStreams = attachmentsInputStreams;
    }

}
