package com.example.calculatorremix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.calculatorremix.databinding.TabContainerBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabLayoutContainer extends Fragment {
    private TabContainerBinding binding;
    private TabLayoutAdapter tabLayoutAdapter;
    private ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TabContainerBinding.inflate(getLayoutInflater(),  container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tabLayoutAdapter = new TabLayoutAdapter(this);
        viewPager = binding.viewPager;
        viewPager.setAdapter(tabLayoutAdapter);

        TabLayout tabLayout = binding.tabLayout;

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(TempConverterFragment.TAB_TITLE);
                    break;
                case 1:
                    tab.setText(TipCalculatorFragment.TAB_TITLE);
                    break;
                case 2:
                    tab.setText(DistanceConverterFragment.TAB_TITLE);
                    break;
            }
        }).attach();
    }
}
