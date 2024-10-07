package pelanggan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.qkeglf.felaundry.PelangganActivity;
import com.qkeglf.felaundry.R;

import java.util.UUID;

import Model.ModelPelanggan;
import database.SQLiteHelper;

public class LayananAddActivity extends AppCompatActivity {
    TextView nama;
    TextView layanan;
    TextView jumlah;
    Button btnSimpan, btnBatal;
    SQLiteHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_layanan_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        nama = (android.widget.TextView) findViewById(R.id.edLayAddSet);
        layanan = (android.widget.TextView) findViewById(R.id.edLayAddSetr);
        jumlah = (android.widget.TextView) findViewById(R.id.edLayAddJum);
        btnSimpan = (Button) findViewById(R.id.btnLayAddSimpan);
        btnBatal = (Button) findViewById(R.id.btnLayAddBatal);

        db = new SQLiteHelper(LayananAddActivity.this);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelPelanggan mp = new ModelPelanggan();
                String uniqueID = UUID.randomUUID().toString();
                mp.setId(""+uniqueID);
                mp.setNama(nama.getText().toString());
                mp.setEmail(layanan.getText().toString());
                mp.setHp(jumlah.getText().toString());

                Toast.makeText(LayananAddActivity.this, ""+mp.getId()+mp.getNama()+mp.getEmail()+mp.getHp(), Toast.LENGTH_SHORT).show();
                boolean cek = db.insertPelanggan(mp);
                if (cek == true){
                    Toast.makeText(LayananAddActivity.this, "Data Disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LayananAddActivity.this, PelangganActivity.class));
                    finish();
                }else {
                    Toast.makeText(LayananAddActivity.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}