package com.avantadi.miscontactos.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avantadi.miscontactos.R;
import com.avantadi.miscontactos.adapter.ContactoAdaptador;
import com.avantadi.miscontactos.pojo.Contacto;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    ArrayList<Contacto> contactos;
    private RecyclerView RecyclerViewListaContactos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        RecyclerViewListaContactos = (RecyclerView) v.findViewById(R.id.rvContactos);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewListaContactos.setLayoutManager(llm);

         /*
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        RecyclerViewListaContactos.setLayoutManager(glm);
         */

        inicializarRecyclerViewListaContactos();

        ContactoAdaptador adaptador = new ContactoAdaptador(contactos, getActivity());
        RecyclerViewListaContactos.setAdapter(adaptador);

        return v;
    }
    public void inicializarRecyclerViewListaContactos(){
        //array list de contactos
        contactos = new ArrayList<Contacto>();
        contactos.add(new Contacto(R.drawable.icecream_circle,"Santiago Finish","1252332","salu@email.com"));
        contactos.add(new Contacto(R.drawable.donut_circle,"Felipe Lotas","34656265","fepe@email.com"));
        contactos.add(new Contacto(R.drawable.froyo_circle,"Juan José","234566754","juanjo@email.com"));
        contactos.add(new Contacto(R.drawable.icecream_circle,"Luis Pelotas","234656667","luispe@email.com"));

    }
}
