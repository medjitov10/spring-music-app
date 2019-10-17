package com.ga.entity;

public class JwtResponse {
    private String jtw;

    public String getToken() {
        return this.jtw;
    }

    public JwtResponse(String jtw) {
        this.jtw = jtw; }

}
