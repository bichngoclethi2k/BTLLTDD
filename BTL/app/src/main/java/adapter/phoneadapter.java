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

public class phoneadapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arrayphone;

    public phoneadapter(Context context, ArrayList<sanpham> arrayphone) {
        this.context = context;
        this.arrayphone = arrayphone;
    }

    @Override
    public int getCount() {
        return arrayphone.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayphone.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
    public TextView txttenspphone, txtgiaspphone, txtmotaspphone;
    public ImageView imgphone;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder=new ViewHolder();//thiếu dòng khởi tạo
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_phone, null);
            viewHolder.txttenspphone=(TextView)convertView.findViewById(R.id.txtviewtenspphone);
            viewHolder.txtgiaspphone=(TextView)convertView.findViewById(R.id.txtviewgiaspphone);
            viewHolder.txtmotaspphone=(TextView)convertView.findViewById(R.id.txtviewmotaphone);
            viewHolder.imgphone=(ImageView)convertView.findViewById(R.id.imageviewphone);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();

        }
        sanpham sanpham= (model.sanpham) getItem(i);
        viewHolder.txttenspphone.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiaspphone.setText("Gia:"+decimalFormat.format(sanpham.getGiasanpham())+"đ");
        viewHolder.txtmotaspphone.setMaxLines(2);
        viewHolder.txtmotaspphone.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotaspphone.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .into(viewHolder.imgphone);
        return convertView;
    }
}
