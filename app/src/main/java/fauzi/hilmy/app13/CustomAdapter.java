package fauzi.hilmy.app13;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fauzi.hilmy.app13.ResponseServer.ResponseItem;

/**
 * Created by 26FaUZeE02 on 4/27/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    List<ResponseItem> data = new ArrayList<>();
    String nama, namanative, region, subregion, luas, urlgambar, codematauang, namamatauang, simbolmatauang;

    public CustomAdapter(Context context, List<ResponseItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterra, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("RAM", "" + data.get(position).getName());
        holder.txtCountry.setText(data.get(position).getName());
        holder.txtSubRegion.setText(data.get(position).getSubRegion());
        Picasso.with(context)
                .load(data.get(position).getFlagPng())
                .placeholder(R.mipmap.ic_launcher)
                .resize(300, 200)
                .into(holder.img);
        nama = data.get(position).getName();
        namanative = data.get(position).getNativeName();
        region = data.get(position).getRegion();
        subregion = data.get(position).getSubRegion();
        luas = String.valueOf(data.get(position).getArea());
        urlgambar = data.get(position).getFlagPng();
        codematauang = data.get(position).getCurrencyCode();
        namamatauang = data.get(position).getCurrencyName();
        simbolmatauang = data.get(position).getCurrencySymbol();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentA = new Intent(context, DetailNegara.class);

                intentA.putExtra("namanegara", nama);
                intentA.putExtra("namanativenegara", namanative);
                intentA.putExtra("regionnegara", region);
                intentA.putExtra("subregionnegara", subregion);
                intentA.putExtra("luasnegara", luas);
                intentA.putExtra("urlgambarnegara", urlgambar);
                intentA.putExtra("codematauang", codematauang);
                intentA.putExtra("namamatauang", namamatauang);
                intentA.putExtra("simbolmatauang", simbolmatauang);

                context.startActivity(intentA);

            }
        });


    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtCountry, txtSubRegion;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgCountry);
            txtCountry = itemView.findViewById(R.id.txtCountry);
            txtSubRegion = itemView.findViewById(R.id.txtSubRegion);
        }
    }
}
