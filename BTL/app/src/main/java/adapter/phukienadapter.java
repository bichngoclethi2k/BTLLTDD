package adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.sanpham;

public class phukienadapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arrayphukien;

    public phukienadapter(Context context, ArrayList<sanpham> arrayphukien) {
        this.context = context;
        this.arrayphukien = arrayphukien;
    }

    @Override
    public int getCount() {
        return arrayphukien.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayphukien.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txttenspphukien, txtgiaspphukien, txtmotaspphukien;
        public ImageView imgphukien;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        phukienadapter.ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder=new phukienadapter.ViewHolder();//thiếu dòng khởi tạo
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_chung, null);
            viewHolder.txttenspphukien=(TextView)convertView.findViewById(R.id.txtviewtenspchung);
            viewHolder.txtgiaspphukien=(TextView)convertView.findViewById(R.id.txtviewgiaspchung);
            viewHolder.txtmotaspphukien=(TextView)convertView.findViewById(R.id.txtviewmotachung);
            viewHolder.imgphukien=(ImageView)convertView.findViewById(R.id.imageviewchung);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(phukienadapter.ViewHolder) convertView.getTag();

        }
        sanpham sanpham= (model.sanpham) getItem(i);
        viewHolder.txttenspphukien.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiaspphukien.setText("Gia:"+decimalFormat.format(sanpham.getGiasanpham())+"đ");
        viewHolder.txtmotaspphukien.setMaxLines(2);
        viewHolder.txtmotaspphukien.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotaspphukien.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .into(viewHolder.imgphukien);
        return convertView;
    }
}
