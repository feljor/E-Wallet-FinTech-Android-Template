package com.mercipro.ewallet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mercipro.ewallet.AddWalletsActivity;
import com.mercipro.ewallet.MainActivity;
import com.mercipro.ewallet.R;
import com.mercipro.ewallet.TransactionsActivity;
import com.mercipro.ewallet.WalletsActivity;
import com.volcaniccoder.bottomify.BottomifyNavigationView;
import com.volcaniccoder.bottomify.OnNavigationItemChangeListener;

public class MainHomeFragment extends Fragment {
    private MainActivity activity;
    private BottomifyNavigationView bottomifyNavigationViewLight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        return inflater.inflate(R.layout.fragment_main_home, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomifyNavigationViewLight = view.findViewById(R.id.bottomify_nav_light);

        //bottomify light
        bottomifyNavigationViewLight.setActiveNavigationIndex(0);
        bottomifyNavigationViewLight.setOnNavigationItemChangedListener(new OnNavigationItemChangeListener() {
            @Override
            public void onNavigationItemChanged(@NonNull BottomifyNavigationView.NavigationItem navigationItem) {
                switch (navigationItem.getPosition()){
                    case 0:
                        loadFragment(new HomeFragment());
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), TransactionsActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), AddWalletsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), WalletsActivity.class));
                        break;
                    case 4:
                        loadFragment(new AccountFragment());
                        break;

                }
            }
        });


        loadFragment(new HomeFragment());

    }

    //----load fragment----------------------
    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .addToBackStack(null)
                    .commit();

            return true;
        }

        return false;

    }


}