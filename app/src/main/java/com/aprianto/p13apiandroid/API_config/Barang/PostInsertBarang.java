package com.aprianto.p13apiandroid.API_config.Barang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostInsertBarang {
    @SerializedName("hasil")
    @Expose
    private List<ModelBarang> hasil = null;

    public List<ModelBarang> getHasil() {
        return hasil;
    }

    public void setHasil(List<ModelBarang> hasil) {
        this.hasil = hasil;
    }
}
