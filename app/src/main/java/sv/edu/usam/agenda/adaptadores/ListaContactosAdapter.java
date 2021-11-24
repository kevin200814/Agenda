package sv.edu.usam.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sv.edu.usam.agenda.R;
import sv.edu.usam.agenda.VerificarActivity;
import sv.edu.usam.agenda.entidades.Contactos;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;

    public ListaContactosAdapter(ArrayList<Contactos> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contactos, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.tvwNombre.setText(listaContactos.get(position).getNombre());
        holder.tvwTelefono.setText(listaContactos.get(position).getTelefono());
        holder.tvwCorreo.setText(listaContactos.get(position).getCorreo());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView tvwNombre,tvwTelefono,tvwCorreo;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvwNombre = itemView.findViewById(R.id.tvNombre);
            tvwTelefono = itemView.findViewById(R.id.tvTelefono);
            tvwCorreo = itemView.findViewById(R.id.tvCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerificarActivity.class);
                    intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
