package activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
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
import adapter.phukienadapter;
import model.loaisp;
import model.sanpham;
import util.Checkconect;
import util.server;

public class phukienactivity extends AppCompatActivity {

    Toolbar toolbarphukien;
    ListView listViewphukien;
    ListView listViewphukienmenu;
    ArrayList<model.loaisp> mangloaisp;
    adapter.loaispadapter loaispadter;
    adapter.phukienadapter phukienadapter;
    ArrayList<model.sanpham> mangphukien;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    int idphukien=0;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    int page=1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phukienactivity);
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
    private void loadata() {
        listViewphukien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(),chitietspactivity.class);
                intent.putExtra("thongtinsanpham",mangphukien.get(i));
                startActivity(intent);
            }
        });
    }

    private void getdata(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdanphone= server.duongdanphone +String.valueOf(Page) ;
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdanphone, response -> {
            int idphukien = 0;
            String Tenphukien = "";
            int Giaphukien = 0;
            String Hinhanhphukien = "";
            String Motaphukien = "";
            int IDloaisp = 0;
            if(response !=null){
                try {
                    JSONArray jsonArray= new JSONArray(response);
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        idphukien=jsonObject.getInt("id");
                        Tenphukien=jsonObject.getString( "tensp");
                        Giaphukien=jsonObject.getInt("giasp");
                        Hinhanhphukien=jsonObject.getString("hinhanhsp");
                        Motaphukien=jsonObject.getString(  "mota");
                        IDloaisp=jsonObject.getInt("idloaisp");
                        mangphukien.add(new sanpham(idphukien,Tenphukien,Giaphukien,Hinhanhphukien,Motaphukien,IDloaisp));
                        phukienadapter.notifyDataSetChanged();
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
                param.put("idloaisp", String.valueOf(idphukien));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void clickitemlistview() {
        listViewphukienmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (Checkconect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(phukienactivity.this, MainActivity2.class);
                            startActivity(intent);
                        } else {
                            Checkconect.showtoast_short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phukienactivity.this,phoneactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phukienactivity.this,laptopactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phukienactivity.this,phukienactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phukienactivity.this,lienheactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(phukienactivity.this,thongtinactivity.class);
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
        setSupportActionBar(toolbarphukien);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarphukien.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarphukien.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void getidloaisp() {
        idphukien= getIntent().getIntExtra("idLoaiSanPham",-1);
        Log.d("giatriloaisp",idphukien +"");
    }

    private void Anhxa() {
        toolbarphukien = findViewById(R.id.toolbarphukien);
        navigationView= findViewById(R.id.navigationview);
        drawerLayout= findViewById(R.id.drawerlayoutphukien);
        listViewphukien= findViewById(R.id.listviewphukien);
        listViewphukienmenu=findViewById(R.id.listviewphukienmenu);
        mangphukien= new ArrayList<>();
        phukienadapter= new phukienadapter(getApplicationContext(),mangphukien);
        listViewphukien.setAdapter(phukienadapter);
        mangloaisp= new ArrayList<>();
        mangloaisp.add(0, new loaisp(0, "Home", "https://loading.io/s/icon/4ul4je.png"));
        loaispadter= new loaispadapter(mangloaisp,getApplicationContext());
        listViewphukienmenu.setAdapter(loaispadter);
    }
}