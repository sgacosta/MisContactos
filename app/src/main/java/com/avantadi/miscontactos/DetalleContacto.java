package com.avantadi.miscontactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class DetalleContacto extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;
    private ImageView ivFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        //Action Bar
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        //activandpo la navegación hacia atrás
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);




        Bundle intentParametros = getIntent().getExtras();
        String nombre = intentParametros.getString("intentNombre");
        String telefono = intentParametros.getString("intentTelefono");
        String email = intentParametros.getString("intentEmail");
        Integer foto = intentParametros.getInt("intentFoto");


        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        ivFoto = (ImageView)  findViewById(R.id.ivFoto);

        //menu de contexto
        registerForContextMenu(tvNombre);


        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        ivFoto.setImageResource(foto);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetalleContacto.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void llamarTelefono(View view) {
        /*
        Intent intento = new Intent(Intent.ACTION_CALL);
        intento.setData(Uri.parse("tel:" + tvTelefono.getText().toString()));
        startActivity(intento);
         */
        int CALL_PHONE_PERMISSION_CODE = 100;

        String telefono = tvTelefono.getText().toString();
        Snackbar.make(findViewById(R.id.layOutDetalleContacto), "Teléfono: " + telefono, Snackbar.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
        } else {
            Snackbar.make(findViewById(R.id.layOutDetalleContacto), "Tiene que autorizar permiso de llamada para realizar esta acción", Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.CALL_PHONE },
                    CALL_PHONE_PERMISSION_CODE);
        }

    }

    public void enviarEmail(View view) {
        String email = tvEmail.getText().toString();
        Intent intento = new Intent(Intent.ACTION_SEND,Uri.parse("mailto:"));
        intento.putExtra(Intent.EXTRA_EMAIL,email );
        intento.setType("message/rfc822");
        try{
            startActivity(Intent.createChooser(intento, "Envío email:"));
        }catch (Exception  e){
            Snackbar.make(findViewById(R.id.layOutDetalleContacto), "No se pudo acceder al programa de correo (" + e.getMessage() + ")", Snackbar.LENGTH_SHORT).show();
        }



    }

    //menu contexto ---------------------------------------------------------------------------------
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mDelete:
                Snackbar.make(tvNombre,getResources().getString(R.string.menu_title_mDelete),Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.mEdit:
                Snackbar.make(tvNombre,getResources().getString(R.string.menu_title_mDelete),Snackbar.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    //menu popup ---------------------------------------------------------------------------------
    public void levantarMenuPopUp(final View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mView:
                        Snackbar.make(v,getResources().getString(R.string.menu_title_mView),Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.mViewDetail:
                        Snackbar.make(v,getResources().getString(R.string.menu_title_mViewDetail),Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }

}