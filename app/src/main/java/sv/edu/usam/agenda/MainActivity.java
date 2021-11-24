package sv.edu.usam.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sv.edu.usam.agenda.adaptadores.ListaContactosAdapter;
import sv.edu.usam.agenda.db.BDContactos;
import sv.edu.usam.agenda.db.DBHelper;
import sv.edu.usam.agenda.entidades.Contactos;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    FloatingActionButton fltNuevo;
    RecyclerView listContactos;
    ArrayList<Contactos> listArrayContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listContactos = findViewById(R.id.ListaContactos);
        listContactos.setLayoutManager(new LinearLayoutManager(this));
        BDContactos bdcontc = new BDContactos(getApplicationContext());
        listArrayContactos = new ArrayList<>();
        ListaContactosAdapter adaptador = new ListaContactosAdapter(bdcontc.mostrarContactos());
        listContactos.setAdapter(adaptador);

        //btnCreate = findViewById(R.id.btnCrear);
        //btnCreate.setVisibility(View.INVISIBLE);
        fltNuevo = findViewById(R.id.fltIcoAdd);

        /* Boton de crear la base de datos
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbhp = new DBHelper(MainActivity.this);
                SQLiteDatabase db = dbhp.getWritableDatabase();

                if (db != null) {
                    Toast.makeText(getApplicationContext(), "Base de datos creada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error en crear la base de datos", Toast.LENGTH_LONG).show();
                }
            }
        });
         */

        fltNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CRUDActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    private void nuevoRegistro() {
        Intent intent = new Intent(this, CRUDActivity.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuInsertar: nuevoRegistro();
            return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
 }