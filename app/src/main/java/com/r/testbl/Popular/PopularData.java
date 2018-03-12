package com.r.testbl.Popular;

/**
 * Created by Unknown on 3/10/2018.
 */

public class PopularData {
    private String namaBarang;
    private String hargaBarang;
    private String ratingBarang;
    private String countBarang;
    private String imageBarang;

    public PopularData(String namaBarang, String hargaBarang, String ratingBarang, String countBarang, String imageBarang) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.ratingBarang = ratingBarang;
        this.countBarang = countBarang;
        this.imageBarang = imageBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public String getRatingBarang() {
        return ratingBarang;
    }

    public String getCountBarang() {
        return countBarang;
    }

    public String getImageBarang() {
        return imageBarang;
    }
}
