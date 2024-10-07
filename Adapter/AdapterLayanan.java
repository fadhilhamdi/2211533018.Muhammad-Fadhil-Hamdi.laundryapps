package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.qkeglf.felaundry.LayananActivity;
import com.qkeglf.felaundry.R;

import java.util.ArrayList;
import java.util.List;

import Model.ModelLayanan;
import Model.ModelPelanggan;

public class AdapterLayanan extends
        RecyclerView.Adapter<AdapterLayanan.ViewHolder> {
    private static final String TAG =
            AdapterLayanan.class.getSimpleName();
    private Context context;
    private List<ModelLayanan> list;
    private View.OnClickListener onItemClicked;

    public AdapterLayanan(LayananActivity context, ArrayList<ModelPelanggan> list) {
    }


    public void setOnItemClickListener(View.OnClickListener
                                               itemClickListener) {
        onItemClicked = itemClickListener;
    }
    public AdapterLayanan(Context context, List<ModelLayanan> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layanan,
                        parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModelLayanan item = list.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvJum.setText(item.getJumlah());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvJum;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tvItemLayNama);
            tvJum = (TextView) itemView.findViewById(R.id.tvItemLayJum);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClicked);

        }
    }

}
