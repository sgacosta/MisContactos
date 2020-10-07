package com.avantadi.miscontactos.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PaginaAdaptador extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentos;

    public PaginaAdaptador(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> fragmentos) {
        super(fm, behavior);
        this.fragmentos = fragmentos;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentos.get(position);
    }

    @Override
    public int getCount() {
        return fragmentos.size();
    }
}
