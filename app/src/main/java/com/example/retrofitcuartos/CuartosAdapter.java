package com.example.retrofitcuartos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CuartosAdapter extends RecyclerView.Adapter<CuartosAdapter.ViewHolder> implements View.OnClickListener {
    private List<Cuartos> cuartoslist;
    private View.OnClickListener listener;

    public CuartosAdapter(List<Cuartos> cuartoslist) {
        this.cuartoslist = cuartoslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent,false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(cuartoslist.get(position).getNombre());
        holder.tvBody.setText(cuartoslist.get(position).getPropietario());
        holder.tvID.setText(cuartoslist.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return cuartoslist.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null)
        {
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvBody;
        TextView tvID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvBody= itemView.findViewById(R.id.tvBody);
            tvID=itemView.findViewById(R.id.tvID);
        }
    }
}
