package sv.edu.usam.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sv.edu.usam.agenda.db.BDContactos;
import sv.edu.usam.agenda.entidades.Contactos;

public class VerificarActivity extends AppCompatActivity{
    EditText edtNombre, edtTelefono, edtCorreo;
    Button btnActualizar;
    Contactos contacto;
    FloatingActionButton fltEdit, flDelete;
    int id = 0;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        edtNombre = findViewById(R.id.edtNombrev);
        edtTelefono = findViewById(R.id.edtTelefonov);
        edtCorreo = findViewById(R.id.edtCorreov);
        btnActualizar = findViewById(R.id.btnActualizar);

        fltEdit = findViewById(R.id.fltIcoEdit);
        flDelete = findViewById(R.id.fltIcoDelete);

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                id = Integer.parseInt(null);
            } else {
                id = extra.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        BDContactos bdContactos = new BDContactos(VerificarActivity.this);
        contacto = bdContactos.verContactos(id);
        if (contacto != null) {

            edtNombre.setText(contacto.getNombre());
            edtTelefono.setText(contacto.getTelefono());
            edtCorreo.setText(contacto.getCorreo());
            btnActualizar.setVisibility(View.INVISIBLE);
			edtNombre.setInputType(InputType.TYPE_NULL);
			edtTelefono.setInputType(InputType.TYPE_NULL);
			edtCorreo.setInputType(InputType.TYPE_NULL);
        }

        fltEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerificarActivity.this, Editar.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        flDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerificarActivity.this);
                builder.setMessage("¿Desea eliminar el registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (bdContactos.eliminarContacto(id)) {
                                    Lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Lista();
                            }
                        }).show();
            }
        });
    }

    public  void Lista() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}