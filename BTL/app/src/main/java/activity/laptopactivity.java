package activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.laptopadapter;
import adapter.loaispadapter;
import model.loaisp;
import model.sanpham;
import util.Checkconect;
import util.server;

public class laptopactivity extends AppCompatActivity {
    Toolbar toolbarlaptop;
    ListView listViewlaptop;
    ListView listViewlaptopmenu;
    ArrayList<model.loaisp> mangloaisp;
    adapter.loaispadapter loaispadter;
    adapter.laptopadapter laptopadapter;
    ArrayList<model.sanpham> manglaptop;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    int idlaptop=0;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    int page=1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptopactivity);
        Anhxa();
        if (Checkconect.haveNetworkConnection(getApplication())){
            getidloaisp();
            actionbar();
            getdulieuloaisp();
            clickitemlistview();
            getdata(page);
        }
        else {
            Checkconect.showtoast_short(getApplicationContext(),"Kiem tra lai ket noi INTERNET cua ban!");
            finish();
        }

    }

    private void getdata(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdanphone= server.duongdanphone +String.valueOf(Page) ;
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdanphone, response -> {
            int idlaptop = 0;
            String Tenlaptop = "";
            int Gialaptop = 0;
            String Hinhanhlaptop = "";
            String Motalaptop = "";
            int IDloaisp = 0;
            if(response !=null){
                try {
                    JSONArray jsonArray= new JSONArray(response);
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        idlaptop=jsonObject.getInt("id");
                        Tenlaptop=jsonObject.getString( "tensp");
                        Gialaptop=jsonObject.getInt("giasp");
                        Hinhanhlaptop=jsonObject.getString("hinhanhsp");
                        Motalaptop=jsonObject.getString(  "mota");
                        IDloaisp=jsonObject.getInt("idloaisp");
                        manglaptop.add(new sanpham(idlaptop,Tenlaptop,Gialaptop,Hinhanhlaptop,Motalaptop,IDloaisp));
                        laptopadapter.notifyDataSetChanged();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
            ;        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param= new HashMap<String, String>();
                param.put("idloaisp", String.valueOf(idlaptop));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void clickitemlistview() {
        listViewlaptopmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (Checkconect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(laptopactivity.this, MainActivity2.class);
                            startActivity(intent);
                        } else {
                            Checkconect.showtoast_short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(laptopactivity.this,phoneactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(laptopactivity.this,laptopactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(laptopactivity.this,lienheactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(laptopactivity.this,thongtinactivity.class);
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
                mangloaisp.add(3,new loaisp(0, "Lien He","https://loading.io/s/icon/2nmu0k.png"));
                mangloaisp.add(4,new loaisp(0, "Thong Tin","https://loading.io/s/icon/8jlasu.png"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Checkconect.showtoast_short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void actionbar() {
        setSupportActionBar(toolbarlaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlaptop.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarlaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void getidloaisp() {
        idlaptop= getIntent().getIntExtra("idLoaiSanPham",-1);
        Log.d("giatriloaisp",idlaptop +"");
    }

    private void Anhxa() {
        toolbarlaptop = findViewById(R.id.toolbarlaptop);
        navigationView= findViewById(R.id.navigationviewlaptop);
        drawerLayout= findViewById(R.id.drawerlayoutlaptop);
        listViewlaptop= findViewById(R.id.listviewlaptop);
        listViewlaptopmenu=findViewById(R.id.listviewlaptopmenu);
        manglaptop= new ArrayList<>();
        laptopadapter= new laptopadapter(getApplicationContext(),manglaptop);
        listViewlaptop.setAdapter(laptopadapter);
        mangloaisp= new ArrayList<>();
        mangloaisp.add(0, new loaisp(0, "Home", "https://loading.io/s/icon/4ul4je.png"));
        loaispadter= new loaispadapter(mangloaisp,getApplicationContext());
        listViewlaptopmenu.setAdapter(loaispadter);
    }
}