package itesm.a01019108.concesionapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.net.Uri;

import itesm.a01019108.concesionapp.dummy.DummyContent.DummyItem;

// REF: https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305
public class ClientMainActivity extends AppCompatActivity
        implements ClientProfileFragment.OnFragmentInteractionListener ,ClientPromosFragment.OnListFragmentInteractionListener,
        CarsFragment.OnFragmentInteractionListener, CarsListFragment.OnListFragmentInteractionListener, CarUsedAddFragment.OnFragmentInteractionListener,
        CarSearchFragment.OnFragmentInteractionListener
{
    Fragment carsFragment =  new CarsFragment();
    Fragment clientPromosFragment =  new ClientPromosFragment();
    Fragment clientProfileFragment =  new ClientProfileFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_cars:
                    openFragment(carsFragment);
                    return true;
                case R.id.navigation_promos:
                    openFragment(clientPromosFragment);
                    return true;
                case R.id.navigation_profile:
                    openFragment(clientProfileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        openFragment(carsFragment);
    }


    @Override
    public void onClientProfileFragmentInteraction(Uri uri) {
        // Do stuff
    }

    @Override
    public void onClientPromosFragmentInteraction(DummyItem item) {
        // Do stuff
    }


    @Override
    public void onCarsFragmentInteraction(Uri uri) {
        // Do stuff
    }

    @Override
    public void onCarsListFragmentInteraction(DummyItem item){
        Intent myIntent = new Intent(ClientMainActivity.this,CarDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putString("id",item.id);
        extras.putString("content",item.content);
        extras.putString("details",item.details);
        myIntent.putExtra("data",extras);
        ClientMainActivity.this.startActivity(myIntent);
    }

    @Override
    public void onCarsUsedAddFragmentInteraction(Uri uri){

    }
    @Override
    public void onCarSearchFragmentInteraction(Uri uri){

    }


    private void openFragment(Fragment fragment ) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, "client");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
