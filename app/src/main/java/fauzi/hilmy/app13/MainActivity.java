package fauzi.hilmy.app13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import fauzi.hilmy.app13.ApiRetrofit.ApiService;
import fauzi.hilmy.app13.ApiRetrofit.instanceRetrofit;
import fauzi.hilmy.app13.ResponseServer.ResponseItem;
import fauzi.hilmy.app13.ResponseServer.ResponseReadData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.volley.VolleyLog.d;

public class MainActivity extends AppCompatActivity {

    RecyclerView view;
    List<ResponseItem> dataItems = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progres);
        view = (RecyclerView) findViewById(R.id.rvData);
        view.setLayoutManager(new LinearLayoutManager(this));
//        toRupiahFormat2();
        getData();
    }

    public void toRupiahFormat2(String nominal) {

        DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(dfs);

        // String rupiah = df.format(Double.parseDouble(nominal)) + ",-";
        //df.setDecimalSeparatorAlwaysShown(desimal);
        df.setMaximumFractionDigits(0);
        df.format(nominal);
//        String rupiah =
//        return rupiah;
    }

    private void getData() {
        ApiService api = instanceRetrofit.getInstance();
        Call<ResponseReadData> call = api.response_read_data();
        call.enqueue(new Callback<ResponseReadData>() {
            @Override
            public void onResponse(Call<ResponseReadData> call, Response<ResponseReadData> response) {
                Boolean status = response.body().isIsSuccess();
                Log.d("TAG", "" + response.body().isIsSuccess());
                if (status) {
                    progressBar.setVisibility(View.GONE);
                    dataItems = response.body().getResponse();
                    CustomAdapter adapter = new CustomAdapter(MainActivity.this, dataItems);
                    view.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseReadData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
