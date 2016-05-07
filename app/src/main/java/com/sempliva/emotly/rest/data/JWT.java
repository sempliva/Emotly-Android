package com.sempliva.emotly.rest.data;

/**
 * Created by ggc on 28/04/16.
 */
public class JWT extends DefaultResponse{
    public Header header;
    public Payload payload;
    public String signature;
}
