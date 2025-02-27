package com.example.calculatorremix;

import android.annotation.SuppressLint;
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

import com.example.calculatorremix.databinding.FragmentTempConverterBinding;

public class TempConverterFragment extends Fragment {
    private static final String TAG = "TempFragment";
    public static final String TAB_TITLE = "Convert Temp";
    public static final String ARG_ID = "tempID";

    private double celsiusValue, fahrenheitValue;
    private EditText fahrenheitEdit, celsiusEdit;
    private String fahrenheitText, celsiusText;
    private FragmentTempConverterBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTempConverterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.convertTempButton.setOnClickListener(v -> convertTemp());
    }
    private void setValue() {
        try{
            celsiusEdit = binding.celsiusEdit;
            celsiusText = celsiusEdit.getText().toString().trim();
            fahrenheitEdit = binding.fahrenEdit;
            fahrenheitText = fahrenheitEdit.getText().toString().trim();

            // Check if just a decimal was entered
            if (celsiusText.equals(".")) {
                celsiusValue = 0.0;
                binding.celsiusEdit.setText("0");
            }
            else if (fahrenheitText.equals(".")) {
                fahrenheitValue = 0.0;
                binding.fahrenEdit.setText("0");
            }

            // Find numeric values
            if (!celsiusText.equals("") && !celsiusText.equals(".")) {
                celsiusValue = Double.parseDouble(celsiusText);
            }
            if (!fahrenheitText.equals("") && !fahrenheitText.equals(".")) {
                fahrenheitValue = Double.parseDouble(fahrenheitText);
            }
        }
        catch(Exception error){
            Log.e(TAG, error.toString());
            error.printStackTrace();
        }
    }

    public void convertTemp() {
        setValue();
        // Neither text entries filled
        if (celsiusText.isEmpty() && fahrenheitText.isEmpty()){
            Toast toast = Toast.makeText(requireContext(), "Please enter a value in either fields.",Toast.LENGTH_LONG);
            toast.show();
        }
        // Celsius filled, fahrenheit empty. Convert c to f and display.
        else if (!celsiusText.isEmpty() && fahrenheitText.isEmpty()) {
            fahrenheitValue = celsToFahren(celsiusValue);
            binding.fahrenEdit.setText(String.valueOf(String.format("%.5g%n", fahrenheitValue)));
        }
        // Fahrenheit filled, celsius empty. OR both are filled. Convert f to c and display.
        else if ( (!fahrenheitText.isEmpty() && celsiusText.isEmpty()  )  ||
                (!fahrenheitText.isEmpty() && !celsiusText.isEmpty() ) ) {
            celsiusValue = fahrenToCels(fahrenheitValue);
            binding.celsiusEdit.setText(String.valueOf(String.format("%.5g%n", celsiusValue)));
        }
        // Do nothing
        else {
            Toast toast = Toast.makeText(requireContext(), "I'm sorry, I don't understand.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private double fahrenToCels(double f){
        return ((f-32.0)*5)/9.0;
    }

    private double celsToFahren(double c){
        return (c * (9.0/5)) + 32.0;
    }

}