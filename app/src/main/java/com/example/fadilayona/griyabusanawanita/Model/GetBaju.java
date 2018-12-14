package com.example.fadilayona.griyabusanawanita.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetBaju {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Baju> result = new ArrayList<Baju>();
    @SerializedName("message")
    private String message;
    public GetBaju() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Baju> getResult() {
        return result;
    }

    public void setResult(List<Baju> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
