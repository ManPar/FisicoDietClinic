package com.fisicodietclinic.fisicodietclinic.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fisicodietclinic.fisicodietclinic.R;
import com.fisicodietclinic.fisicodietclinic.models.Query;

import java.util.List;


public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.MyViewHolder> {

    private List<Query> queryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, discription, reply;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.id_title);
            discription = (TextView) view.findViewById(R.id.id_discription);
            reply = (TextView) view.findViewById(R.id.id_reply);
        }
    }


    public QueryAdapter(List<Query> queryList) {
        this.queryList = queryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.query_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Query query = queryList.get(position);
        holder.title.setText(query.getTitle());
        holder.discription.setText(query.getDiscription());
        holder.reply.setText("Reply  :  " + query.getReply());
    }

    @Override
    public int getItemCount() {
        return queryList.size();
    }
}