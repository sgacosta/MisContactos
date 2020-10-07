package com.avantadi.miscontactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.avantadi.miscontactos.adapter.PaginaAdaptador;
import com.avantadi.miscontactos.fragment.PerfilFragment;
import com.avantadi.miscontactos.fragment.RecyclerViewFragment;
import com.avantadi.miscontactos.pojo.Contacto;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 //RecyclerViewListaContactos pasa a frangment ...
 //   ArrayList<Contacto> contactos;
 //   private RecyclerView RecyclerViewListaContactos;

    private Toolbar toolbar;
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Action Bar
        //Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        //setSupportActionBar(miActionBar);

        ////activandpo la navegación hacia atrás
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

//RecyclerViewListaContactos pasa a frangment ...
//        RecyclerViewListaContactos = (RecyclerView) findViewById(R.id.rvContactos);
//        /*
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        RecyclerViewListaContactos.setLayoutManager(llm);
//        */
//        GridLayoutManager glm = new GridLayoutManager(this,2);
//        RecyclerViewListaContactos.setLayoutManager(glm);
//        inicializarRecyclerViewListaContactos();
//        ContactoAdaptador adaptador = new ContactoAdaptador(contactos,this);
//        RecyclerViewListaContactos.setAdapter(adaptador);


        /*
        ArrayList<String> nombresContacto = new ArrayList<String>();
        for (Contacto contacto: contactos         ) {
            nombresContacto.add(contacto.getNombre());
        }
        //lista
        final ListView lstContactos = (ListView) findViewById(R.id.lstContactos);
        //adaptador
        ArrayAdapter<String> arrayAdaptador = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,nombresContacto);
        //asociación adaptador a lista
        lstContactos.setAdapter(arrayAdaptador);

        //listener en lista
        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento = new Intent(MainActivity.this,DetalleContacto.class);
                intento.putExtra(getString(R.string.intentNombre),contactos.get(position).getNombre().toString());
                intento.putExtra(getString(R.string.intentTelefono),contactos.get(position).getTelefono().toString());
                intento.putExtra(getString(R.string.intentEmail),contactos.get(position).getEmail().toString());
                startActivity(intento);
                finish();
            }
        });
        */

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tablayout = (TabLayout) findViewById(R.id.tablayout) ;
        viewpager = (ViewPager) findViewById(R.id.viewPager) ;
        setUpViewPager();

        if(toolbar != null){
            setSupportActionBar(toolbar);

        }

    }

    //lista de fragmentos
    private ArrayList<Fragment> agregarFragmentos(){
        ArrayList<Fragment> fragmentos = new ArrayList<>();
        fragmentos.add(new RecyclerViewFragment());
        fragmentos.add(new PerfilFragment());
        return fragmentos;
    }

    //establecer ViewPager con el adaptador correspondiente
    private void setUpViewPager(){
        viewpager.setAdapter(new PaginaAdaptador(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,agregarFragmentos()));
        tablayout.setupWithViewPager(viewpager);

        tablayout.getTabAt(0).setIcon(R.drawable.ic_baseline_format_list_bulleted_24);
        tablayout.getTabAt(1).setIcon(R.drawable.ic_baseline_person_24);

    }

 //RecyclerViewListaContactos pasa a frangment ...
 //   public void inicializarRecyclerViewListaContactos(){
 //       //array list de contactos
 //       contactos = new ArrayList<Contacto>();
 //       contactos.add(new Contacto(R.drawable.icecream_circle,"Santiago Finish","1252332","salu@email.com"));
 //       contactos.add(new Contacto(R.drawable.donut_circle,"Felipe Lotas","34656265","fepe@email.com"));
 //       contactos.add(new Contacto(R.drawable.froyo_circle,"Juan José","234566754","juanjo@email.com"));
 //       contactos.add(new Contacto(R.drawable.icecream_circle,"Luis Pelotas","234656667","luispe@email.com"));
//
 //   }

    //menu opciones ---------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        //return true;
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
            case R.id.mAbout:
                Snackbar.make(getCurrentFocus(),getResources().getString(R.string.menu_title_mAbout),Snackbar.LENGTH_SHORT).show();
                break;
           case R.id.mSettings:
               Snackbar.make(getCurrentFocus(),getResources().getString(R.string.menu_title_mSettings),Snackbar.LENGTH_SHORT).show();
               break;
           case R.id.mQuit:
               Snackbar.make(getCurrentFocus(),getResources().getString(R.string.menu_title_mQuit),Snackbar.LENGTH_SHORT).show();
               finish();
               break;
           case R.id.mRefresh:
               Snackbar.make(getCurrentFocus(),getResources().getString(R.string.menu_title_mRefresh),Snackbar.LENGTH_SHORT).show();
               break;

        }

        return super.onOptionsItemSelected(item);
    }

}