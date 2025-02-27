package com.example.calculatorremix;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.calculatorremix.databinding.FragmentTipCalculatorBinding;

import java.util.Locale;

public class TipCalculatorFragment extends Fragment {

    public static final String TAB_TITLE = "Calc Tip";
    public static final String ARG_ID = "tipID";
    private FragmentTipCalculatorBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTipCalculatorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.calculateTipButton.setOnClickListener(v -> calculateTip());
    }

    private boolean validateInputs() {
        String[] inputFields = {binding.tipPercentEdit.getText().toString().trim(),
                                binding.numPeopleEdit.getText().toString().trim(),
                                binding.totalBillEdit.getText().toString().trim()};

        for (String inputField : inputFields) {
            // zero or empty
            if (inputField.equals("0") || inputField.isEmpty()) {
                return false;
            }
            //  non-numeric
            else if (!inputField.matches("^[\\d\\-.]+$")) {
                return  false;
            }
        }

        return true;
    }

    private void calculateTip() {
        // Validate
        boolean goodInput = validateInputs();
        Integer numPeople;
        Double totalBill;
        Double tipPercent;
        Double billPerPerson;
        if (goodInput) {
            // Convert to numerics
            try {
                tipPercent = Double.parseDouble(binding.tipPercentEdit.getText().toString().trim());
                totalBill = Double.parseDouble(binding.totalBillEdit.getText().toString().trim());
                numPeople = Integer.parseInt(binding.numPeopleEdit.getText().toString().trim());
            }
            catch (NumberFormatException e) {
                return;
            }

            // Calculate
            tipPercent = tipPercent / 100.00;
            Double totalTip = tipPercent * totalBill;
            Double tipPerPerson = totalTip / numPeople;
            billPerPerson = tipPerPerson + (totalBill / numPeople);

            // Format billPerPerson into currency string
            String billPerPersonString = String.format(Locale.getDefault(), "%.2f",billPerPerson);
            billPerPersonString = getString(R.string.bill_per_person) + " $" + billPerPersonString;

            // Set View text for billPerPerson
            binding.billPerPersonView.setText(billPerPersonString);

        }
        else { // Bad input display toast
            Toast toast = Toast.makeText(requireContext(), "Invalid Input", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
