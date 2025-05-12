package com.paper.Recyclers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.paper.ModelClases.ServicesPojo;
import com.paper.carhub.AboutService;
import com.paper.carhub.R;

import java.util.ArrayList;

public class ServiceRecycler extends RecyclerView.Adapter<ServiceRecycler.ViewHolder> {
    Context context;
    ArrayList<ServicesPojo> list;

    public ServiceRecycler(Context context, ArrayList<ServicesPojo> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ServiceRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.servicesholder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceRecycler.ViewHolder holder, int position) {
        ServicesPojo servicesPojo = list.get(position);
        Glide.with(context).load(servicesPojo.getServiceLogoimage()).into(holder.servicelogo);
        holder.remaintime.setText(servicesPojo.getServiceRemains());
        holder.typeofservice.setText(servicesPojo.getServiceName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getAdapterPosition()==holder.getAdapterPosition()){
                    Intent intent = new Intent(context, AboutService.class);
                    intent.putExtra("Servicename",servicesPojo.getServiceName());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView servicelogo;
        TextView remaintime,typeofservice;
        LinearLayout approval;
        FrameLayout detailindetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            servicelogo = itemView.findViewById(R.id.servicelogo);
            remaintime = itemView.findViewById(R.id.remaintime);
            typeofservice = itemView.findViewById(R.id.typeofservice);
            approval = itemView.findViewById(R.id.approval);
            detailindetail = itemView.findViewById(R.id.detailindetail);
        }
    }
}
