package com.example.tiptime;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tiptime.databinding.ActivityMainBinding;
import java.text.NumberFormat;
import org.jetbrains.annotations.Nullable;

public final class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        this.binding = binding;
        binding = this.binding;
        if (binding == null) {
            throw new IllegalArgumentException("binding");
        }

        this.setContentView((View)binding.getRoot());
        if (this.binding == null) {
            throw new IllegalArgumentException("binding");
        }

        this.binding.calculateButton.setOnClickListener((OnClickListener)(new OnClickListener() {
            public void onClick(View it) {
                MainActivity.this.calculateTip();
            }
        }));
        if (this.binding == null) {
            throw new IllegalArgumentException("binding");
        }

        this.binding.costOfServiceEditText.setOnKeyListener((OnKeyListener)(new OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent $noName_2) {
                return MainActivity.this.handleKeyEvent(view, keyCode);
            }
        }));
    }

    private void calculateTip() {
        if (this.binding == null) {
            throw new IllegalArgumentException("binding");
        }

        String stringInTextField = String.valueOf(this.binding.costOfServiceEditText.getText());
        Double cost = Double.parseDouble(stringInTextField);
        if (cost == null && cost == 0.0) {
            this.displayTip(0.0);
        }

        double tipPercentage;
        switch(this.binding.tipOptions.getCheckedRadioButtonId()) {
            case (R.id.option_twenty_percent):
                tipPercentage = 0.20;
                break;
            case (R.id.option_eighteen_percent):
                tipPercentage = 0.18;
                break;
            default:
                tipPercentage = 0.15;
        }

        double tip = tipPercentage * cost;
        if (this.binding == null) {
            throw new IllegalArgumentException("binding");
        }

        boolean roundUp = this.binding.roundUpSwitch.isChecked();
        if (roundUp) {
            tip = Math.ceil(tip);
        }

        this.displayTip(tip);
    }

    private void displayTip(double tip) {
        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        if (this.binding == null) {
            throw new IllegalArgumentException("binding");
        }

        this.binding.tipResult.setText((CharSequence)this.getString(R.string.tip_amount, new Object[]{formattedTip}));
    }

    private boolean handleKeyEvent(View view, int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            Object binding = this.getSystemService(Context.INPUT_METHOD_SERVICE);
            InputMethodManager inputMethodManager = (InputMethodManager)binding;
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }
}
