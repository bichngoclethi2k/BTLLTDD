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

public class laptopadapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arraylaptop;

    public laptopadapter(Context context, ArrayList<sanpham> arraylaptop) {
        this.context = context;
        this.arraylaptop = arraylaptop;
    }

    @Override
    public int getCount() {
        return arraylaptop.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylaptop.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txttensplaptop, txtgiasplaptop, txtmotasplaptop;
        public ImageView imglaptop;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        laptopadapter.ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder=new ViewHolder();//thiếu dòng khởi tạo
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_chung, null);
            viewHolder.txttensplaptop=(TextView)convertView.findViewById(R.id.txtviewtenspchung);
            viewHolder.txtgiasplaptop=(TextView)convertView.findViewById(R.id.txtviewgiaspchung);
            viewHolder.txtmotasplaptop=(TextView)convertView.findViewById(R.id.txtviewmotachung);
            viewHolder.imglaptop=(ImageView)convertView.findViewById(R.id.imageviewchung);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();

        }
        sanpham sanpham= (model.sanpham) getItem(i);
        viewHolder.txttensplaptop.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiasplaptop.setText("Gia:"+decimalFormat.format(sanpham.getGiasanpham())+"đ");
        viewHolder.txtmotasplaptop.setMaxLines(2);
        viewHolder.txtmotasplaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasplaptop.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .into(viewHolder.imglaptop);
        return convertView;
    }
}
