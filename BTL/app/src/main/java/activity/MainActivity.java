package activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText edtusername, edtpasswd;
    Button btndn, btndk, btnthoat;
    String ten,mk;
    FirebaseAuth mAuthencation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        controlButton();
        mAuthencation =FirebaseAuth.getInstance();
    }


    private void controlButton() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Ban co muon thoat");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                       onBackPressed();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog =new Dialog (MainActivity.this);
                dialog.setTitle("From Dang Ky");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.customdialog);
                EditText edtusernamedk= (EditText)dialog.findViewById(R.id.usernamedk);
                EditText edtpasswddk= (EditText)dialog.findViewById(R.id.passworddk);
                Button btndk1= (Button)dialog.findViewById(R.id.btndk1);
                Button btnhuy=(Button)dialog.findViewById(R.id.btnhuy);
                btndk1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ten= edtusernamedk.getText().toString().trim();
                        mk= edtpasswddk.getText().toString().trim();
                        edtusername.setText(ten);
                        edtpasswd.setText(mk);
                        dialog.cancel();

                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtusername.getText().length() !=0 && edtpasswd.getText().length()!=0){
                    if (edtusername.getText().toString().equals(ten)&& edtpasswd.getText().toString().equals(mk)){
                        Toast.makeText(MainActivity.this,"Ban dn thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                    else if (edtusername.getText().toString().equals("ngoc")&& edtpasswd.getText().toString().equals("12345678")){
                        Toast.makeText(MainActivity.this,"Ban dn thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"User name or passwd fail", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Username or Passwd empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Anhxa(){
        edtusername=(EditText)findViewById(R.id.username);
        edtpasswd=(EditText)findViewById(R.id.password);
        btndk=(Button)findViewById(R.id.btndk);
        btndn=(Button)findViewById(R.id.btndn);
        btnthoat=(Button)findViewById(R.id.btnthoat);
    }
}