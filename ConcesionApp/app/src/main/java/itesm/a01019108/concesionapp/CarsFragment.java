package itesm.a01019108.concesionapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CarsListFragment autosNuevosFragment =  new CarsListFragment();
    CarUsedAddFragment carUsedAddFragment =  new CarUsedAddFragment();
    CarSearchFragment carSearchFragment =  new CarSearchFragment();
    int lastPosition = 0;

    FloatingActionButton actionButton;
    String tag;

    Tab agregarTab;
    TabLayout tabNavigation;
    private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Log.w("onTabSelected",""+tab.getPosition());
            int position = tab.getPosition();
            switch(position){
                case 0:
                    if(lastPosition == 2)
                        openFragment(autosNuevosFragment);
                        actionButton.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    Log.w("onTabSelected","c"+autosNuevosFragment.changeContent());
                    if(lastPosition == 2)
                        openFragment(autosNuevosFragment);
                        actionButton.setVisibility(View.VISIBLE);

                    break;
                case 2:
                    actionButton.setVisibility(View.INVISIBLE);
                    openFragment(carUsedAddFragment);
                    break;
            }
            lastPosition = position;
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


    private void openFragment(Fragment fragment ) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.autosContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private OnFragmentInteractionListener mListener;

    public CarsFragment() {
        // Required empty public constructor
        carSearchFragment.setCallback(new OnSearchInterface() {
            @Override
            public void onEndSearch(String result) {
                // Do something when download finished
                Log.w("End search","end search: "+result);
                openFragment(autosNuevosFragment);
                actionButton.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarsFragment newInstance(String param1, String param2) {
        CarsFragment fragment = new CarsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        openFragment(autosNuevosFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tag = getTag();
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_cars, container, false);

        tabNavigation = (TabLayout) fragmentView.findViewById(R.id.tabNavigation);
        tabNavigation.addOnTabSelectedListener(mOnTabSelectedListener);

        actionButton = (FloatingActionButton) fragmentView.findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionButton.setVisibility(View.INVISIBLE);
                goToSearch();
            }
        });
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        agregarTab = tabNavigation.getTabAt(2);
        if(tag == "seller"){
            tabNavigation.removeTab(agregarTab);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCarsFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCarsFragmentInteraction(Uri uri);
    }

    public void goToSearch(){
        openFragment(carSearchFragment);
    }

}
