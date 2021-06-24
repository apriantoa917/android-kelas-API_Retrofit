package com.aprianto.p13apiandroid.Barang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aprianto.p13apiandroid.API_config.ApiService;
import com.aprianto.p13apiandroid.API_config.ApiUtils;
import com.aprianto.p13apiandroid.API_config.Barang.GetDataBarang;
import com.aprianto.p13apiandroid.API_config.Barang.ModelBarang;
import com.aprianto.p13apiandroid.API_config.Barang.PostInsertBarang;
import com.aprianto.p13apiandroid.R;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBarang extends AppCompatActivity {

    private ApiService api_services;
    TextView tv_hasil;
    EditText ed_kode, ed_nama, ed_satuan, ed_hjual, ed_hbeli, ed_diskon;
    Button btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init layout element
        tv_hasil = findViewById(R.id.tv_hasil);
        ed_kode = findViewById(R.id.ed_kode);
        ed_nama = findViewById(R.id.ed_nama);
        ed_satuan = findViewById(R.id.ed_satuan);
        ed_hjual = findViewById(R.id.ed_hjual);
        ed_hbeli = findViewById(R.id.ed_hbeli);
        ed_diskon = findViewById(R.id.ed_diskon);
        btn_simpan = findViewById(R.id.btn_simpan);

        api_services = ApiUtils.getApiService();


        btn_simpan.setOnClickListener(view -> {
            insert_data(
                    ed_kode.getText().toString(),
                    ed_nama.getText().toString(),
                    ed_satuan.getText().toString(),
                    ed_hjual.getText().toString(),
                    ed_hbeli.getText().toString(),
                    ed_diskon.getText().toString()
            );
        });

        read_data();
    }

    public void read_data(){
        api_services.getDataBarang().enqueue(new Callback<GetDataBarang>() {
            @Override
            public void onResponse(Call<GetDataBarang> call, Response<GetDataBarang> response) {
                if(response.isSuccessful()){
                    Log.d("ISI_DATA",response.body().toString());

                    // Mapping gson result ke list untuk ditampilkan
                    List<ModelBarang> data_barang = response.body().getHasil();

                    String out_print = "";
                    for(ModelBarang barang:data_barang){
                        out_print = out_print+" "+barang.getKode()+" "+barang.getNama()+" "+barang.getSatuan()+" "+barang.getHargajual()+" "+barang.getHargabeli()+" "+barang.getDiskon()+"\n";
                    }
                    tv_hasil.setText(out_print);
                }
            }

            @Override
            public void onFailure(Call<GetDataBarang> call, Throwable t) {
            }
        });
    }

    public void insert_data(String kode, String nama, String satuan, String hjual, String hbeli, String diskon){
        /*
        ⚠ SESUAIKAN VARIABEL PARAMS PADA API INSERT (POST BODY)
         */
        HashMap<String, String> map = new HashMap<>();
        map.put("kode",kode);
        map.put("nama",nama);
        map.put("satuan",satuan);
        map.put("hjual",hjual);
        map.put("hbeli",hbeli);
        map.put("diskon",diskon);
        api_services.postInsertBarang(map).enqueue(new Callback<PostInsertBarang>() {
            @Override
            public void onResponse(Call<PostInsertBarang> call, Response<PostInsertBarang> response) {
                if(response.isSuccessful()){
                    Log.d("POST_DATA",response.body().toString());
                    Toast.makeText(getApplicationContext(),"Barang "+kode+" berhasil ditambahkan.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostInsertBarang> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("POST_DATA",t.toString()+"\n---\n"+call.toString());
            }
        });
        reset_input();
        read_data();
    }

    public void reset_input(){
        ed_kode.setText("");
        ed_nama.setText("");
        ed_satuan.setText("");
        ed_hjual.setText("");
        ed_hbeli.setText("");
        ed_diskon.setText("");
    }
}