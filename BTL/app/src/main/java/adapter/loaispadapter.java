package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.loaisp;


public class loaispadapter extends BaseAdapter {
    ArrayList<loaisp> arrayListloaisp;
    Context context;

    public loaispadapter(ArrayList<loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size() ;
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null)
        {   viewHolder= new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_listview_loaisp, null);
            viewHolder.txttenloaisp=(TextView)convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp=(ImageView)convertView.findViewById(R.id.imageviewloaisp);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();

        }
        loaisp loaisp= (model.loaisp) getItem(position);
        viewHolder.txttenloaisp.setText(loaisp.getTenloaisp());
        Picasso.get().load(loaisp.getHinhanhloaisp())
                .into(viewHolder.imgloaisp);
        return  convertView;

    }


}
