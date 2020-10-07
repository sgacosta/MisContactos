package com.avantadi.miscontactos.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avantadi.miscontactos.pojo.Contacto;
import com.avantadi.miscontactos.DetalleContacto;
import com.avantadi.miscontactos.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {

    private ArrayList<Contacto> Contactos;
    private Activity activity;


    //al llamar al Constructor se guardan los datos en el ArrayLisy
    public ContactoAdaptador(ArrayList<Contacto> contactos,Activity activity) {
        this.Contactos = contactos;
        this.activity = activity;
    }

    //infla el layout cardview_contacto
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //asociar el layout al reclyclerView: se infla el layout que va a reciclar el recyclerView (cardview_contacto)
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false);
        //el layout a reciclar (cardview_contacto) ya inflado, se pasa al constructor ContactoViewHolder (para que obtenga cada View) y se retorna
        return new ContactoViewHolder(v) ;
    }

    //se pasa la lista a cada elemento y se asocia cada elemento con su View
    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder contactoViewHolder, int position) {
        final Contacto contacto = Contactos.get(position);
        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());
        contactoViewHolder.tvTelefonoCV.setText(contacto.getTelefono());
        contactoViewHolder.btnLike.setImageResource(R.drawable.icons8_thumbs_up_24_grey);
        contactoViewHolder.btnLike.setTag(R.drawable.icons8_thumbs_up_24_grey);


        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,contacto.getNombre(),Snackbar.LENGTH_SHORT).show();
                //Toast.makeText(activity,contacto.getNombre(),Toast.LENGTH_SHORT).show();
                Intent intento = new Intent(activity, DetalleContacto.class);
                intento.putExtra("intentNombre",contacto.getNombre().toString());
                intento.putExtra("intentTelefono",contacto.getTelefono().toString());
                intento.putExtra("intentEmail",contacto.getEmail().toString());
                intento.putExtra("intentFoto",contacto.getFoto());
                activity.startActivity(intento);
                //no termino la actividad porque tengo en el menú el icono de arriba
                //activity.finish();
            }
        });
        contactoViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Like en " + contacto.getNombre(),Snackbar.LENGTH_SHORT).show();
                ImageButton btnLikeClick;
                btnLikeClick = (ImageButton) v.findViewById(R.id.btnLike);

                switch(get_DrawableId(btnLikeClick)) {
                    case R.drawable.icons8_thumbs_up_24_green:
                        btnLikeClick.setImageResource(R.drawable.icons8_thumbs_up_24_grey);
                        btnLikeClick.setTag(R.drawable.icons8_thumbs_up_24_grey);
                        break;
                    case R.drawable.icons8_thumbs_up_24_grey:
                        btnLikeClick.setImageResource(R.drawable.icons8_thumbs_up_24_green);
                        btnLikeClick.setTag(R.drawable.icons8_thumbs_up_24_green);
                        break;
                    default:
                        btnLikeClick.setImageResource(R.drawable.icons8_thumbs_up_24_green);
                        btnLikeClick.setTag(R.drawable.icons8_thumbs_up_24_green);
                        break;
                }

            }
        });


    }
    private int get_DrawableId(ImageView iv) {
        return (Integer) iv.getTag();
    }
    //cantiadad de elementos que contiene la lista
    @Override
    public int getItemCount() {
        return Contactos.size();
    }


    //esta clase es static
    public static class ContactoViewHolder extends RecyclerView.ViewHolder{

        private  ImageView imgFoto;
        private  TextView tvNombreCV;
        private  TextView tvTelefonoCV;
        private ImageButton btnLike;

        //Constructor: se pasa la vista cardview_contacto
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto         = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV      = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV    = (TextView) itemView.findViewById(R.id.tvTelefonoCV);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);

        }
    }


}
