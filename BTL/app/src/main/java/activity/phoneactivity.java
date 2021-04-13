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

import adapter.loaispadapter;
import adapter.phoneadapter;
import model.loaisp;
import model.sanpham;
import util.Checkconect;
import util.server;

public class phoneactivity extends AppCompatActivity {
    Toolbar toolbarphone;
    ListView listViewphone;
    ListView listViewphonemenu;
    ArrayList<model.loaisp> mangloaisp;
    adapter.loaispadapter loaispadter;
    adapter.phoneadapter phoneadapter;
    ArrayList<model.sanpham> mangphone;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    int idphone=0;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    int page=1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneactivity);
        Anhxa();
        if (Checkconect.haveNetworkConnection(getApplication())){
            getidloaisp();
            actionbar();
            getdulieuloaisp();
            clickitemlistview();
            getdata(page);
            loadata();
        }
        else {
            Checkconect.showtoast_short(getApplicationContext(),"Kiem tra lai ket noi INTERNET cua ban!");
            finish();
        }

    }
    private void loadata() {
        listViewphone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(),chitietspactivity.class);
                intent.putExtra("thongtinsanpham",mangphone.get(i));
                startActivity(intent);
            }
        });
    }

    private void getdata(int Page) {
        RequestQueue requestQueue =Volley.newRequestQueue(getApplicationContext());
        String duongdanphone= server.duongdanphone +String.valueOf(Page) ;
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdanphone, response -> {
            int idphone = 0;
            String Tenphone = "";
            int Giaphone = 0;
            String Hinhanhphone = "";
            String Motaphone = "";
            int IDloaisp = 0;
            if(response !=null){
                try {
                    JSONArray jsonArray= new JSONArray(response);
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        idphone=jsonObject.getInt("id");
                        Tenphone=jsonObject.getString( "tensp");
                        Giaphone=jsonObject.getInt("giasp");
                        Hinhanhphone=jsonObject.getString("hinhanhsp");
                        Motaphone=jsonObject.getString(  "mota");
                        IDloaisp=jsonObject.getInt("idloaisp");
                        mangphone.add(new sanpham(idphone,Tenphone,Giaphone,Hinhanhphone,Motaphone,IDloaisp));
                        phoneadapter.notifyDataSetChanged();
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
                param.put("idloaisp", String.valueOf(idphone));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void clickitemlistview() {
        listViewphonemenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (Checkconect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(phoneactivity.this, MainActivity2.class);
                            startActivity(intent);
                        } else {
                            Checkconect.showtoast_short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phoneactivity.this,phoneactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phoneactivity.this,laptopactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phoneactivity.this,phukienactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phoneactivity.this,lienheactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phoneactivity.this,thongtinactivity.class);
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
                mangloaisp.add(4,new loaisp(0, "Lien He","https://loading.io/s/icon/2nmu0k.png"));
                mangloaisp.add(5,new loaisp(0, "Thong Tin","https://loading.io/s/icon/8jlasu.png"));
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
        setSupportActionBar(toolbarphone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarphone.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarphone.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void getidloaisp() {
        idphone= getIntent().getIntExtra("idLoaiSanPham",-1);
        Log.d("giatriloaisp",idphone +"");
   }

    private void Anhxa() {
        toolbarphone = findViewById(R.id.toolbarphone);
        navigationView= findViewById(R.id.navigationviewphone);
        drawerLayout= findViewById(R.id.drawerlayoutphone);
        listViewphone= findViewById(R.id.listviewphone);
        listViewphonemenu=findViewById(R.id.listviewphonemenu);
        mangphone= new ArrayList<>();
        phoneadapter= new phoneadapter(getApplicationContext(),mangphone);
        listViewphone.setAdapter(phoneadapter);
        mangloaisp= new ArrayList<>();
        mangloaisp.add(0, new loaisp(0, "Home", "https://loading.io/s/icon/4ul4je.png"));
        loaispadter= new loaispadapter(mangloaisp,getApplicationContext());
        listViewphonemenu.setAdapter(loaispadter);
    }
}