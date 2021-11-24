package sv.edu.usam.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.usam.agenda.db.BDContactos;

public class CRUDActivity extends AppCompatActivity {

    EditText edtxtNombre, edtxtTelefono, edtxtEmail;
    Button btnInsertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudactivity);

        edtxtNombre = findViewById(R.id.edtNombre);
        edtxtTelefono = findViewById(R.id.edtTelefono);
        edtxtEmail = findViewById(R.id.edtCorreo);
        btnInsertar = findViewById(R.id.btnInsert);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long status;
                BDContactos contactBD = new BDContactos(getApplicationContext());
                status = contactBD.insertarContacto(
                        edtxtNombre.getText().toString(),
                        edtxtTelefono.getText().toString(),
                        edtxtEmail.getText().toString()
                );

                if (status > 0) {
                    edtxtNombre.setText("");
                    edtxtTelefono.setText("");
                    edtxtEmail.setText("");
                    Toast.makeText(getApplicationContext(), "Registro almacenado correctamente", Toast.LENGTH_LONG).show();
                    listar();
                } else {
                    Toast.makeText(getApplicationContext(), "Registro no almacenado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    private void listar() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_listar: listar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}