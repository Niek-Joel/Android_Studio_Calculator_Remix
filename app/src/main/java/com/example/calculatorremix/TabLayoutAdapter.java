package com.example.calculatorremix;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabLayoutAdapter extends FragmentStateAdapter {
    public static final int NUM_TABS = 3;

    public TabLayoutAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        Bundle args = new Bundle();

        switch (position) {
            case 0:
                fragment = new TempConverterFragment();
                args.putInt(TempConverterFragment.ARG_ID, 0);
                break;
            case 1:
                fragment = new TipCalculatorFragment();
                args.putInt(TipCalculatorFragment.ARG_ID, 1);
                break;
            case 2:
                fragment = new DistanceConverterFragment();
                args.putInt(DistanceConverterFragment.ARG_ID, 2);
                break;
            default:
                throw new IllegalArgumentException("Invalid position. Value must be from 0 to " + (NUM_TABS - 1) + " (counting from 0). Given position = " + position);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
