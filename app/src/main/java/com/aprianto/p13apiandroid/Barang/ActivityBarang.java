package com.aprianto.p13apiandroid.Barang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.aprianto.p13apiandroid.API_config.Barang.PostDeleteBarang;
import com.aprianto.p13apiandroid.API_config.Barang.PostInsertBarang;
import com.aprianto.p13apiandroid.API_config.Barang.PostUpdateBarang;
import com.aprianto.p13apiandroid.R;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBarang extends AppCompatActivity implements listenerBarang {

    private ApiService api_services;
    TextView tv_hasil;
    EditText ed_kode, ed_nama, ed_satuan, ed_hjual, ed_hbeli, ed_diskon;
    Button btn_save, btn_update, btn_delete, btn_cancel, btn_refresh;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init layout element
//        tv_hasil = findViewById(R.id.tv_hasil);
        ed_kode = findViewById(R.id.ed_kode);
        ed_nama = findViewById(R.id.ed_nama);
        ed_satuan = findViewById(R.id.ed_satuan);
        ed_hjual = findViewById(R.id.ed_hjual);
        ed_hbeli = findViewById(R.id.ed_hbeli);
        ed_diskon = findViewById(R.id.ed_diskon);
        btn_save = findViewById(R.id.btn_save);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        btn_refresh = findViewById(R.id.btn_refresh);
        btn_cancel = findViewById(R.id.btn_cancel);
        recyclerView = findViewById(R.id.recyclerView);

        api_services = ApiUtils.getApiService();


        btn_save.setOnClickListener(view -> insert_data(
                ed_kode.getText().toString(),
                ed_nama.getText().toString(),
                ed_satuan.getText().toString(),
                ed_hjual.getText().toString(),
                ed_hbeli.getText().toString(),
                ed_diskon.getText().toString()
        ));

        btn_update.setOnClickListener(view -> update_data(
                ed_kode.getText().toString(),
                ed_nama.getText().toString(),
                ed_satuan.getText().toString(),
                ed_hjual.getText().toString(),
                ed_hbeli.getText().toString(),
                ed_diskon.getText().toString()
        ));

        btn_delete.setOnClickListener(view -> delete_data(ed_kode.getText().toString()));

        btn_cancel.setOnClickListener(view -> reset_input());

        btn_refresh.setOnClickListener(view -> {
            read_data();
            Toast.makeText(getApplicationContext(), "Data diperbarui", Toast.LENGTH_SHORT).show();
        });

        reset_input();
    }

    public void read_data() {
        api_services.getDataBarang().enqueue(new Callback<GetDataBarang>() {
            @Override
            public void onResponse(Call<GetDataBarang> call, Response<GetDataBarang> response) {
                if (response.isSuccessful()) {
                    Log.d("ISI_DATA", response.body().toString());

                    // Mapping gson result ke list untuk ditampilkan
                    List<ModelBarang> data_barang = response.body().getHasil();

                    AdapterBarang adapter = new AdapterBarang(data_barang, getApplicationContext(), ActivityBarang.this::onItemClicked);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setNestedScrollingEnabled(false);


//                    String out_print = "";
//                    for(ModelBarang barang:data_barang){
//                        out_print = out_print+" "+barang.getKode()+" "+barang.getNama()+" "+barang.getSatuan()+" "+barang.getHargajual()+" "+barang.getHargabeli()+" "+barang.getDiskon()+"\n";
//                    }
//                    tv_hasil.setText(out_print);
                }
            }

            @Override
            public void onFailure(Call<GetDataBarang> call, Throwable t) {
            }
        });
    }

    public void insert_data(String kode, String nama, String satuan, String hjual, String hbeli, String diskon) {
        /*
        ⚠ SESUAIKAN VARIABEL PARAMS PADA API INSERT (POST BODY)
         */
        HashMap<String, String> map = new HashMap<>();
        map.put("kode", kode);
        map.put("nama", nama);
        map.put("satuan", satuan);
        map.put("hjual", hjual);
        map.put("hbeli", hbeli);
        map.put("diskon", diskon);
        api_services.postInsertBarang(map).enqueue(new Callback<PostInsertBarang>() {
            @Override
            public void onResponse(Call<PostInsertBarang> call, Response<PostInsertBarang> response) {
                if (response.isSuccessful()) {
                    Log.d("POST_DATA", response.body().toString());
                    Toast.makeText(getApplicationContext(), "Barang " + kode + " berhasil ditambahkan.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostInsertBarang> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("POST_DATA", t.toString() + "\n---\n" + call.toString());
            }
        });
        reset_input();
    }

    public void update_data(String kode, String nama, String satuan, String hjual, String hbeli, String diskon) {
        /*
        ⚠ SESUAIKAN VARIABEL PARAMS PADA API UPDATE (POST BODY)
         */
        HashMap<String, String> map = new HashMap<>();
        map.put("kode", kode);
        map.put("nama", nama);
        map.put("satuan", satuan);
        map.put("hjual", hjual);
        map.put("hbeli", hbeli);
        map.put("diskon", diskon);
        api_services.postUpdateBarang(map).enqueue(new Callback<PostUpdateBarang>() {
            @Override
            public void onResponse(Call<PostUpdateBarang> call, Response<PostUpdateBarang> response) {
                if (response.isSuccessful()) {
                    Log.d("POST_DATA", response.body().toString());
                    Toast.makeText(getApplicationContext(), "Barang " + kode + " berhasil diupdate.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostUpdateBarang> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("POST_DATA", t.toString() + "\n---\n" + call.toString());
            }
        });
        reset_input();
    }

    public void delete_data(String kode) {
        api_services.postDeleteBarang(kode).enqueue(new Callback<PostDeleteBarang>() {
            @Override
            public void onResponse(Call<PostDeleteBarang> call, Response<PostDeleteBarang> response) {
                if (response.isSuccessful()) {
                    Log.d("POST_DATA", response.body().toString());
                    Toast.makeText(getApplicationContext(), "Barang " + kode + " berhasil dihapus.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostDeleteBarang> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("POST_DATA", t.toString() + "\n---\n" + call.toString());
            }
        });
        reset_input();
    }

    public void reset_input() {
        read_data();
        ed_kode.setText("");
        ed_nama.setText("");
        ed_satuan.setText("");
        ed_hjual.setText("");
        ed_hbeli.setText("");
        ed_diskon.setText("");
        btn_save.setEnabled(true);
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_refresh.setEnabled(true);
        btn_cancel.setEnabled(false);
    }

    @Override
    public void onItemClicked(String kode, String nama, String satuan, String hjual, String hbeli, String diskon) {
        btn_save.setEnabled(false);
        btn_update.setEnabled(true);
        btn_delete.setEnabled(true);
        btn_refresh.setEnabled(false);
        btn_cancel.setEnabled(true);
        ed_kode.setText(kode);
        ed_nama.setText(nama);
        ed_satuan.setText(satuan);
        ed_hjual.setText(hjual);
        ed_hbeli.setText(hbeli);
        ed_diskon.setText(diskon);
    }
}
