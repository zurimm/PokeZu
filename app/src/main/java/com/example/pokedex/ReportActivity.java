package com.example.pokedex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ReportActivity extends AppCompatActivity {

    private Button buttonBack;
    private ImageView imageView;

    private static final String TAG = "ReportActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte);

        buttonBack = findViewById(R.id.buttonBack);
        imageView = findViewById(R.id.imageViewReport);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la anterior
            }
        });

        // Configura Retrofit para obtener el reporte
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/") // Cambia la IP si es necesario
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ReportApiService reportApiService = retrofit.create(ReportApiService.class);
        Call<ResponseBody> call = reportApiService.getPokemonReport();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        Log.e(TAG, "Error al procesar la imagen: " + e.getMessage());
                    }
                } else {
                    Log.e(TAG, "Error en la respuesta del reporte: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(TAG, "Error en la llamada al reporte: " + t.getMessage());
            }
        });
    }
}
