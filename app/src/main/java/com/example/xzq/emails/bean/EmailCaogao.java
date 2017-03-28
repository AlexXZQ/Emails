package com.example.xzq.emails.bean;

/**
 * Created by Xzq on 2017/3/28.
 */

public class EmailCaogao {

    private int id;
    private String mailfrom;
    private String mailto;
    private String subject;
    private String content;

    public EmailCaogao(int id, String mailfrom, String mailto, String subject, String content) {
        super();
        this.id = id;
        this.mailfrom = mailfrom;
        this.mailto = mailto;
        this.subject = subject;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMailfrom() {
        return mailfrom;
    }

    public void setMailfrom(String mailfrom) {
        this.mailfrom = mailfrom;
    }

    public String getMailto() {
        return mailto;
    }

    public void setMailto(String mailto) {
        this.mailto = mailto;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
