package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.loaispadapter;
import adapter.sanphamadapter;
import model.loaisp;
import model.sanpham;
import util.Checkconect;
import util.GridSpacingItemDecoration;
import util.server;


public class MainActivity2 extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    ListView listView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ArrayList<model.loaisp> mangloaisp;
    adapter.loaispadapter loaispadter;
    ArrayList<model.sanpham> mangspmoi;
    adapter.sanphamadapter sanphamadapter;
    Animation animation_slide_in;
    Animation animation_slide_out;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Anhxa();

        if (Checkconect.haveNetworkConnection(getApplication())){
            ActionBar();
            ActionViewFliper();
            getdulieuloaisp();
            getdulieuspmoi();
            clickitemlistview();

        }
        else {
            Checkconect.showtoast_short(getApplicationContext(),"Kiem tra lai ket noi INTERNET cua ban!");
            finish();
        }

    }

    private void clickitemlistview() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (Checkconect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
                            startActivity(intent);
                        } else {
                            Checkconect.showtoast_short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(MainActivity2.this,phoneactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(MainActivity2.this,laptopactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(MainActivity2.this,phukienactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(MainActivity2.this,lienheactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(MainActivity2.this,thongtinactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
            }
        });
    }

    private void getdulieuspmoi() {
        RequestQueue requestQueue =Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(server.duongdanspmoi, response -> {
            if (response != null) {
                int ID = 0;
                String Tensanpham = "";
                Integer Giasanpham = 0;
                String Hinhanhsanpham = "";
                String Motasanpham = "";
                int IDsanpham = 0;
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject  =response.getJSONObject(i);
                        ID=jsonObject.getInt("id");
                        Tensanpham=jsonObject.getString( "tensp");
                        Giasanpham=jsonObject.getInt("giasp");
                        Hinhanhsanpham=jsonObject.getString("hinhanhsp");
                        Motasanpham=jsonObject.getString(  "mota");
                        IDsanpham=jsonObject.getInt("idloaisp");
                        mangspmoi.add(new sanpham(ID,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,IDsanpham));
                        sanphamadapter.notifyDataSetChanged();
                    }  catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void getdulieuloaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.duongdan, response -> {
            if (response != null){
                for (int i=0; i< response.length(); i++){
                    try{
                        JSONObject jsonObject=response.getJSONObject(i);
                        id= jsonObject.getInt("id");
                        tenloaisp= jsonObject.getString("tenloaisp");
                        hinhanhloaisp= jsonObject.getString("hinhanhloaisp");
                        mangloaisp.add(new loaisp(id,tenloaisp,hinhanhloaisp));
                        loaispadter.notifyDataSetChanged();

                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                mangloaisp.add(4,new loaisp(0, "Liên Hệ","https://loading.io/s/icon/2nmu0k.png"));
                mangloaisp.add(5,new loaisp(0, "Thông Tin","https://loading.io/s/icon/8jlasu.png"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Checkconect.showtoast_short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    private void ActionViewFliper() {
        int[] quangcao= {R.drawable.lap,R.drawable.vang,R.drawable.ip12,R.drawable.laptop};
        for (int i =0; i<quangcao.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(quangcao[i]);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        animation_slide_in= AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        animation_slide_out= AnimationUtils.loadAnimation(this,R.anim.slide_out_right);
        viewFlipper.setAnimation(animation_slide_in);
        viewFlipper.setAnimation(animation_slide_out);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void Anhxa() {
        toolbar= (Toolbar)findViewById(R.id.toolbarhome);
        viewFlipper=(ViewFlipper)findViewById(R.id.viewflipper);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        listView=(ListView)findViewById(R.id.listviewhome);
        navigationView=(NavigationView)findViewById(R.id.navigationview);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        mangloaisp= new ArrayList<>();
        mangloaisp.add(0, new loaisp(0, "Home", "https://loading.io/s/icon/4ul4je.png"));
        loaispadter= new loaispadapter(mangloaisp,getApplicationContext());
        listView.setAdapter(loaispadter);
        mangspmoi=new ArrayList<>();
        sanphamadapter= new sanphamadapter(getApplicationContext(),mangspmoi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(sanphamadapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,20,true));
    }

}