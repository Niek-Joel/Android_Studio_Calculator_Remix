package com.example.calculatorremix;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calculatorremix.databinding.FragmentDistanceConverterBinding;

public class DistanceConverterFragment extends Fragment {
    private double milesValue, kilometersValue;
    private EditText milesEdit, kilometersEdit;
    private String milesText, kilometersText;
    private FragmentDistanceConverterBinding binding;

    public static final String TAB_TITLE = "Convert Dist";
    public static final String ARG_ID = "distID";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDistanceConverterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.convertDistanceButton.setOnClickListener(v -> convertDistance());
    }

    private void setValue() {
        try{
            kilometersEdit = binding.kilometersEdit;
            kilometersText = kilometersEdit.getText().toString().trim();
            milesEdit = binding.milesEdit;
            milesText = milesEdit.getText().toString().trim();

            // Check if just a decimal was entered
            if (kilometersText.equals(".")) {
                kilometersValue = 0.0;
                binding.kilometersEdit.setText("0");
            }
            else if (milesText.equals(".")) {
                milesValue = 0.0;
                binding.milesEdit.setText("0");
            }

            // Find numeric values
            if (!kilometersText.equals("") && !kilometersText.equals(".")) {
                kilometersValue = Double.parseDouble(kilometersText);
            }
            if (!milesText.equals("") && !milesText.equals(".")) {
                milesValue = Double.parseDouble(milesText);
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
    }

    public void convertDistance() {
        setValue();
        // Neither text entries filled
        if (kilometersText.isEmpty() && milesText.isEmpty()){
            Toast toast = Toast.makeText(requireContext(), "Please enter a value in either fields.",Toast.LENGTH_LONG);
            toast.show();
        }
        // Kilometers filled, miles empty. Convert km to m and display in milesEdit.
        else if (!kilometersText.isEmpty() && milesText.isEmpty()) {
            milesValue = kilometersToMiles(kilometersValue);
            binding.milesEdit.setText(String.valueOf(String.format("%.5g%n", milesValue)));
        }
        // Miles filled and Kilometers empty. OR both are filled. Convert m to km and display in kilometersEdit.
        else if ( (!milesText.isEmpty() && kilometersText.isEmpty()  )  ||
                (!milesText.isEmpty() && !kilometersText.isEmpty() ) ) {
            kilometersValue = milesToKilometers(milesValue);
            binding.kilometersEdit.setText(String.valueOf(String.format("%.5g%n", kilometersValue)));
        }
        // Do nothing
        else {
            Toast toast = Toast.makeText(requireContext(), "I'm sorry, I don't understand.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private double milesToKilometers(double miles){
        return miles * 1.609344;
    }

    private double kilometersToMiles(double kilometers){
        return kilometers * 0.621371;
    }
}