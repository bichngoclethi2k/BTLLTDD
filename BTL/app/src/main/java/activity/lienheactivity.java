package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import model.loaisp;
import util.Checkconect;
import util.server;

public class lienheactivity extends AppCompatActivity {
    Toolbar toolbarlienhe;
    ListView listViewlienhemenu;
    ArrayList<model.loaisp> mangloaisp;
    adapter.loaispadapter loaispadter;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lienheactivity);
        super.onCreate(savedInstanceState);
        Anhxa();
        if (Checkconect.haveNetworkConnection(getApplication())){
            actionbar();
            getdulieuloaisp();
            clickitemlistview();

        }
        else {
            Checkconect.showtoast_short(getApplicationContext(),"Kiem tra lai ket noi INTERNET cua ban!");
            finish();
        }



    }
    private void clickitemlistview() {
        listViewlienhemenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (Checkconect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(lienheactivity.this, MainActivity2.class);
                            startActivity(intent);
                        } else {
                            Checkconect.showtoast_short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(lienheactivity.this,phoneactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(lienheactivity.this,laptopactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(lienheactivity.this,lienheactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(lienheactivity.this,thongtinactivity.class);
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
        setSupportActionBar(toolbarlienhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlienhe.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarlienhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void Anhxa() {
        toolbarlienhe = findViewById(R.id.toolbarlienhe);
        navigationView= findViewById(R.id.navigationviewlienhe);
        drawerLayout= findViewById(R.id.drawerlayoutlienhe);
        listViewlienhemenu=findViewById(R.id.listviewlienhemenu);
        mangloaisp= new ArrayList<>();
        mangloaisp.add(0, new loaisp(0, "Home", "https://loading.io/s/icon/4ul4je.png"));
        loaispadter= new loaispadapter(mangloaisp,getApplicationContext());
        listViewlienhemenu.setAdapter(loaispadter);
    }
}