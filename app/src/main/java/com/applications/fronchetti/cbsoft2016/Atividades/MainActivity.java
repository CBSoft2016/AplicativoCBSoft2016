package com.applications.fronchetti.cbsoft2016.Atividades;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.app.Fragment;

import com.applications.fronchetti.cbsoft2016.Fragmentos.Hospedagem;
import com.applications.fronchetti.cbsoft2016.Fragmentos.Inicio;
import com.applications.fronchetti.cbsoft2016.Fragmentos.Minicursos;
import com.applications.fronchetti.cbsoft2016.Fragmentos.Palestras;
import com.applications.fronchetti.cbsoft2016.Fragmentos.Restaurantes;
import com.applications.fronchetti.cbsoft2016.Fragmentos.Taxi;
import com.applications.fronchetti.cbsoft2016.Fragmentos.Turismo;
import com.applications.fronchetti.cbsoft2016.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    private IProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.titulo_cbsoft);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        profile = new ProfileDrawerItem().withName(name).withEmail(email);

        // Create the AccountHeader
        buildHeader(false, savedInstanceState);

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Página Inicial").withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().withName(R.string.fragmento_palestras).withIcon(FontAwesome.Icon.faw_users),
                        new PrimaryDrawerItem().withName(R.string.fragmento_minicursos).withIcon(FontAwesome.Icon.faw_graduation_cap),
                        new SectionDrawerItem().withName(R.string.secao_cidade),
                        new SecondaryDrawerItem().withName(R.string.fragmento_hotel_hostel).withIcon(FontAwesome.Icon.faw_bed),
                        new SecondaryDrawerItem().withName(R.string.fragmento_onibus).withIcon(FontAwesome.Icon.faw_bus),
                        new SecondaryDrawerItem().withName(R.string.fragmento_taxi).withIcon(FontAwesome.Icon.faw_taxi),
                        new SecondaryDrawerItem().withName(R.string.fragmento_restaurante).withIcon(FontAwesome.Icon.faw_cutlery),
                        new SecondaryDrawerItem().withName(R.string.fragmento_pontos_turisticos).withIcon(FontAwesome.Icon.faw_map_signs)
                )
                // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                Fragment FragmentInicio = new Inicio();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentInicio).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                            case 2:
                                Fragment FragmentPalestras = new Palestras();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentPalestras).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                            case 3:
                                Fragment FragmentMinicursos = new Minicursos();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentMinicursos).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                            case 5:
                                Fragment FragmentHospedagem = new Hospedagem();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentHospedagem).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                            case 6:
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.tccc.mga")));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.tccc.mga")));
                                }
                                break;
                            case 7:
                                Fragment FragmentTaxi = new Taxi();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentTaxi).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                            case 8:
                                Fragment FragmentRestaurantes = new Restaurantes();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentRestaurantes).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                            case 9:
                                Fragment FragmentTurismo = new Turismo();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentTurismo).addToBackStack(null).commit();
                                CloseDrawer();
                                break;
                        }
                        return false;
                    }
                })
                .addStickyDrawerItems(
                        new SecondaryDrawerItem().withName("About").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(10)
                )
                .withSavedInstance(savedInstanceState)
                .build();

        Fragment FragmentInicio = new Inicio();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentInicio).commit();
    }

    public void CloseDrawer(){
        if(result.isDrawerOpen()){
            result.closeDrawer();
        }
    }


    private void buildHeader(boolean compact, Bundle savedInstanceState) {
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withAlternativeProfileHeaderSwitching(false)
                .withProfileImagesVisible(false)
                .withSelectionListEnabled(false)
                .withCompactStyle(compact)
                .addProfiles(
                        profile
                ).withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}