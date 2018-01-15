package com.fisicodietclinic.fisicodietclinic.models;

/**
 * Created by manyamadan on 10/12/17.
 */

public class Query {

    String title;
    String discription;
    String reply;



    public Query(String title,String discription,String reply)
    {
        this.title = title;
        this.discription = discription;
        this.reply = reply;


    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }


    public String getDiscription() {
        return discription;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }
    public String getReply() {
        return reply;
    }
    public void setReply(String reply)
    {
        this.reply=reply;
    }


}
