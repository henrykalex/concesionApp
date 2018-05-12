package itesm.a01019108.concesionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import itesm.a01019108.concesionapp.dummy.DummyContent.DummyItem;

public class SellerMainActivity extends AppCompatActivity
implements CarsFragment.OnFragmentInteractionListener, CarsListFragment.OnListFragmentInteractionListener,
        CarSearchFragment.OnFragmentInteractionListener, CarSalesFragment.OnFragmentInteractionListener,
        CarSalesListFragment.OnListFragmentInteractionListener, CarSalesSearchFragment.OnFragmentInteractionListener,
        SellerProfileFragment.OnFragmentInteractionListener

{
    Fragment carsFragment =  new CarsFragment();
    Fragment carSalesFragment =  new CarSalesListFragment();
    Fragment vendorProfileFragment =  new SellerProfileFragment();

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_cars:
                    openFragment(carsFragment);
                    return true;
                case R.id.navigation_sales:
                    openFragment(carSalesFragment);
                    return true;
                case R.id.navigation_profile:
                    openFragment(vendorProfileFragment);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        openFragment(carsFragment);
    }

    @Override
    public void onCarsFragmentInteraction(Uri uri) {
        // Do stuff
    }
    @Override
    public void onCarsListFragmentInteraction(DummyItem item){
        Intent myIntent = new Intent(SellerMainActivity.this,CarDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putString("id",item.id);
        extras.putString("content",item.content);
        extras.putString("details",item.details);
        myIntent.putExtra("data",extras);
        SellerMainActivity.this.startActivity(myIntent);
    }
    @Override
    public void onCarSearchFragmentInteraction(Uri uri){

    }

    @Override
    public void onCarSalesFragmentInteraction(Uri uri) {
        // Do stuff
    }
    @Override
    public void onCarSalesListFragmentInteraction(DummyItem item) {
        Intent myIntent = new Intent(SellerMainActivity.this,CarSaleDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putString("id",item.id);
        extras.putString("content",item.content);
        extras.putString("details",item.details);
        myIntent.putExtra("data",extras);
        SellerMainActivity.this.startActivity(myIntent);
    }
    @Override
    public void onCarSalesSearchFragmentInteraction(Uri uri) {
        // Do stuff
    }

    @Override
    public void onSellerProfileFragmentInteraction(Uri uri) {
        // Do stuff
    }


    private void openFragment(Fragment fragment ) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment,"seller");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
