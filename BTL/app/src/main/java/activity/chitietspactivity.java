package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.btl.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import model.giohang;
import model.sanpham;

public class chitietspactivity extends AppCompatActivity {
    Toolbar toolbarchitiet;
    ImageView imgchitiet;
    TextView txttenchitiet, txtgiachitiet,txtmotachitiet;
    Spinner spinner;
    Button btnmua;
    int id=0;
    String tenspct="";
    int giaspct=0;
    String hinhspct="";
    String motaspct="";
    int idloaispct=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietspactivity);
        Anhxa();
        Actionbar();
        getinfo();
        catheventspinnert();
        evenbtn();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent= new Intent(getApplicationContext(),activity.giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void evenbtn() {
    btnmua.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (MainActivity2.manggiohang.size()>0){
                int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                boolean exists=false;
                for(int i=0;i<MainActivity2.manggiohang.size();i++){
                    if (MainActivity2.manggiohang.get(i).getIdsp()==id){
                        MainActivity2.manggiohang.get(i).setSoluongsp(MainActivity2.manggiohang.get(i).getSoluongsp()+sl);
                        if (MainActivity2.manggiohang.get(i).getSoluongsp()>=10){
                            MainActivity2.manggiohang.get(i).setSoluongsp(10);
                        }
                        MainActivity2.manggiohang.get(i).setGiasp(giaspct*MainActivity2.manggiohang.get(i).getSoluongsp());
                        exists=true;
                    }
                }
                if(exists==false){
                    int soluong= Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi=soluong*giaspct;
                    MainActivity2.manggiohang.add(new giohang(id,tenspct,giamoi,hinhspct,soluong));
                }
            }else{
                int soluong= Integer.parseInt(spinner.getSelectedItem().toString());
                long giamoi=soluong*giaspct;
                MainActivity2.manggiohang.add(new giohang(id,tenspct,giamoi,hinhspct,soluong));
            }
            Intent intent= new Intent(getApplicationContext(),activity.giohang.class);
            startActivity(intent);
        }
    });
    }

    private void catheventspinnert() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter= new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void getinfo() {

        sanpham sanpham= (model.sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id=sanpham.getID();
        tenspct=sanpham.getTensanpham();
        giaspct=sanpham.getGiasanpham();
        hinhspct=sanpham.getHinhanhsanpham();
        motaspct=sanpham.getMotasanpham();
        idloaispct=sanpham.getIDsanpham();
        txttenchitiet.setText(tenspct);
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");//, chứ ko đc chấm
        txtgiachitiet.setText("Gia:" + decimalFormat.format(giaspct)+"VNĐ");
        txtmotachitiet.setText(motaspct);
        Picasso.get().load(hinhspct)
                .into(imgchitiet);
    }

    private void Actionbar() {
        setSupportActionBar(toolbarchitiet);
        ActionBar actionBar=getSupportActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitiet=(Toolbar) findViewById(R.id.toolbarchitiet);
        imgchitiet=(ImageView) findViewById(R.id.imageviewchitiet);
        txtgiachitiet=(TextView) findViewById(R.id.txtviewgiaspchitiet);
        txttenchitiet=(TextView) findViewById(R.id.txtviewtenspchitiet);
        txtmotachitiet=(TextView) findViewById(R.id.txtviewmotachitiet);
        spinner=(Spinner)findViewById(R.id.spinner);
        btnmua=(Button)findViewById(R.id.buttonmua);
    }
}