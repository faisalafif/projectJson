package com.faislll.projectjson.ddd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("nama_depan")
    @Expose
    private String namaDepan;

    @SerializedName("nama_belakang")
    @Expose(serialize = false)
    private String namaBelakang;

    @Expose
    private int umur;

    @Expose
    private String jurusan;

    public Mahasiswa(String namaDepan, String namaBelakang, int umur, String jurusan) {
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.umur = umur;
        this.jurusan = jurusan;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public int getUmur() {
        return umur;
    }

    public String getJurusan() {
        return jurusan;
    }
}
