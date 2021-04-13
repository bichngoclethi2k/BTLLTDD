package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import activity.chitietspactivity;
import model.sanpham;

public class sanphamadapter extends RecyclerView.Adapter<sanphamadapter.ItemHolder> {
    Context context;
    ArrayList<sanpham> arraysanpham;

    public sanphamadapter(Context context, ArrayList<sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_spmoi,null);
        ItemHolder itemHolder= new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder( @NonNull ItemHolder holder, int i ) {
        sanpham sanpham= arraysanpham.get(i);
        holder.txttensp.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        holder.txtgiasp.setText("Gia:"+decimalFormat.format(sanpham.getGiasanpham())+"đ");
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.lap)
                .error(R.drawable.vang)
                .into(holder.imghinhsanpham);

    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensp, txtgiasp;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham= (ImageView) itemView.findViewById(R.id.imgviewsanpham);
            txttensp=(TextView)itemView.findViewById(R.id.txtviewtensp);
            txtgiasp=(TextView)itemView.findViewById(R.id.txtviewgiasp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, chitietspactivity.class);
                    intent.putExtra("thongtinsanpham",arraysanpham.get(getAdapterPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);
                }
            });
        }
    }
}
