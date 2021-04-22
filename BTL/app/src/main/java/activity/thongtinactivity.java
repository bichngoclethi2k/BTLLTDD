package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
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

public class  thongtinactivity extends AppCompatActivity {

    Toolbar toolbarthongtin;
    ListView listViewthongtinmenu;
    ArrayList<model.loaisp> mangloaisp;
    adapter.loaispadapter loaispadter;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_thongtinactivity);
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
    private void clickitemlistview() {
        listViewthongtinmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (Checkconect.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(thongtinactivity.this, MainActivity2.class);
                            startActivity(intent);
                        } else {
                            Checkconect.showtoast_short(getApplicationContext(), "Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(thongtinactivity.this,phoneactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(thongtinactivity.this,laptopactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(thongtinactivity.this,phukienactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(thongtinactivity.this,lienheactivity.class);
                            intent.putExtra("idLoaiSanPham",mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            Checkconect.showtoast_short(getApplicationContext(),"Bạn kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(Checkconect.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(thongtinactivity.this,thongtinactivity.class);
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
        setSupportActionBar(toolbarthongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthongtin.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarthongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void Anhxa() {
        toolbarthongtin = findViewById(R.id.toolbarthongtin);
        navigationView= findViewById(R.id.navigationviewthongtin);
        drawerLayout= findViewById(R.id.drawerlayoutthongtin);
        listViewthongtinmenu=findViewById(R.id.listviewthongtinmenu);
        mangloaisp= new ArrayList<>();
        mangloaisp.add(0, new loaisp(0, "Home", "https://loading.io/s/icon/4ul4je.png"));
        loaispadter= new loaispadapter(mangloaisp,getApplicationContext());
        listViewthongtinmenu.setAdapter(loaispadter);
    }
}