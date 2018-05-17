package fauzi.hilmy.app13;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNegara extends AppCompatActivity {

    @BindView(R.id.imgNegara)
    ImageView imgNegara;
    @BindView(R.id.txtNegara)
    TextView txtNegara;
    @BindView(R.id.txtRegion)
    TextView txtRegion;
    @BindView(R.id.txtLuasNegara)
    TextView txtLuasNegara;
    @BindView(R.id.txtMataUang)
    TextView txtMataUang;
    @BindView(R.id.fabb)
    FloatingActionButton fabb;
    String namaa, namanativee, region, subregion, luas, codematauang, namamatauang, simbomatauang, urlgambar, luasformat;
    MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_negara);
        ButterKnife.bind(this);
        Intent get = getIntent();
        namaa = get.getStringExtra("namanegara");
        namanativee = get.getStringExtra("namanativenegara");
        region = get.getStringExtra("regionnegara");
        subregion = get.getStringExtra("subregionnegara");
        luas = get.getStringExtra("luasnegara");
        codematauang = get.getStringExtra("codematauang");
        namamatauang = get.getStringExtra("namamatauang");
        simbomatauang = get.getStringExtra("simbolmatauang");
        urlgambar = get.getStringExtra("urlgambarnegara");

        txtNegara.setText("Nama Negara: " + namaa + "/" + namanativee);
        txtRegion.setText("Region Negara: " + subregion + "/" + region);
        txtMataUang.setText("Mata Uang Negara: " + simbomatauang + "/" + codematauang + "/" + namamatauang);
        Picasso.with(activity)
                .load(urlgambar)
                .placeholder(R.mipmap.ic_launcher)
                .resize(1920, 800)
                .into(imgNegara);
//        activity.toRupiahFormat2(luas);

        txtLuasNegara.setText("Luas Negara: " + luas + " km2");
    }

    @OnClick(R.id.fabb)
    public void onViewClicked() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
