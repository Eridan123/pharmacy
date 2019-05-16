package com.app.pharmacy.apteka.model;


public class OutputMessage {

    private Long from;
    private Long order_id;
    private String date;

    public OutputMessage(Long from, Long order_id, String date) {
        this.from = from;
        this.order_id = order_id;
        this.date = date;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
