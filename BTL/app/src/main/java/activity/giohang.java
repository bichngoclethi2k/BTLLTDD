package activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.btl.R;

import java.text.DecimalFormat;

import adapter.giohangadapter;

public class giohang extends AppCompatActivity {
    ListView listViewgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan;
    Toolbar toolbargiohang;
    adapter.giohangadapter giohangadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        Actionbar();
        Checkdata();
        evenutil();
        DeletItem();

    }


    private void DeletItem() {
        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(giohang.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc chắn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity2.manggiohang.size()<=0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity2.manggiohang.remove(position);
                            giohangadapter.notifyDataSetChanged();
                            evenutil();
                            if(MainActivity2.manggiohang.size()<=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else{
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangadapter.notifyDataSetChanged();
                                evenutil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangadapter.notifyDataSetChanged();
                        evenutil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void evenutil() {
        long tongtien=0;
        for(int i=0;i<MainActivity2.manggiohang.size();i++){
            tongtien += MainActivity2.manggiohang.get(i).getGiasp();

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+"Đ");
    }

    private void Checkdata() {
        if(MainActivity2.manggiohang.size()<=0){
            giohangadapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            listViewgiohang.setVisibility(View.INVISIBLE);

        }
        else{
            giohangadapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            listViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void Actionbar() {
        setSupportActionBar(toolbargiohang);
        ActionBar actionBar=getSupportActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void Anhxa() {
        listViewgiohang=(ListView) findViewById(R.id.listviewgiohang);
        txtthongbao=(TextView)findViewById(R.id.txtviewthongbao);
        txttongtien=(TextView)findViewById(R.id.txtviewtongtien);
        btnthanhtoan=(Button)findViewById(R.id.buttonthanhtoangiohang);
        toolbargiohang=(Toolbar) findViewById(R.id.toolbargiohang);
        giohangadapter=new giohangadapter(activity.giohang.this,MainActivity2.manggiohang);
        listViewgiohang.setAdapter(giohangadapter);
    }
}