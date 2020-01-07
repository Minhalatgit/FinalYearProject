package com.minhal.fyp.Model;

public class PIR {
    private Integer detected;
    private String time;

    public PIR(){ }

    public PIR(Integer detected,String time){
        this.detected=detected;
        this.time=time;
    }

    public void setDetect(Integer detected) {
        this.detected = detected;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getDetected() {
        return detected;
    }

    public String getTime() {
        return time;
    }
}
