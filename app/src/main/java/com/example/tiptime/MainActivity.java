package com.example.tiptime;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tiptime.databinding.ActivityMainBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import java.text.NumberFormat;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;

public final class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(binding, "ActivityMainBinding.inflate(layoutInflater)");
        this.binding = binding;
        binding = this.binding;
        if (binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        this.setContentView((View)binding.getRoot());
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        this.binding.calculateButton.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                MainActivity.this.calculateTip();
            }
        }));
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        this.binding.costOfServiceEditText.setOnKeyListener((OnKeyListener)(new OnKeyListener() {
            public final boolean onKey(View view, int keyCode, KeyEvent $noName_2) {
                MainActivity var10000 = MainActivity.this;
                Intrinsics.checkNotNullExpressionValue(view, "view");
                return var10000.handleKeyEvent(view, keyCode);
            }
        }));
    }

    private void calculateTip() {
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        TextInputEditText var8 = this.binding.costOfServiceEditText;
        Intrinsics.checkNotNullExpressionValue(var8, "binding.costOfServiceEditText");
        String stringInTextField = String.valueOf(var8.getText());
        Double cost = StringsKt.toDoubleOrNull(stringInTextField);
        if (cost != null && !Intrinsics.areEqual(cost, 0.0)) {
            if (this.binding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }

            RadioGroup var9 = this.binding.tipOptions;
            Intrinsics.checkNotNullExpressionValue(var9, "binding.tipOptions");
            double var10;
            switch(var9.getCheckedRadioButtonId()) {
                case (R.id.option_twenty_percent):
                    var10 = 0.20;
                    break;
                case (R.id.option_eighteen_percent):
                    var10 = 0.18;
                    break;
                default:
                    var10 = 0.15;
            }

            double tipPercentage = var10;
            double tip = tipPercentage * cost;
            if (this.binding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }

            SwitchMaterial var11 = this.binding.roundUpSwitch;
            Intrinsics.checkNotNullExpressionValue(var11, "binding.roundUpSwitch");
            boolean roundUp = var11.isChecked();
            if (roundUp) {
                tip = Math.ceil(tip);
            }

            this.displayTip(tip);
        } else {
            this.displayTip(0.0D);
        }
    }

    private void displayTip(double tip) {
        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        TextView var4 = this.binding.tipResult;
        Intrinsics.checkNotNullExpressionValue(var4, "binding.tipResult");
        var4.setText((CharSequence)this.getString(R.string.tip_amount, new Object[]{formattedTip}));
    }

    private boolean handleKeyEvent(View view, int keyCode) {
        if (keyCode == 66) {
            Object binding = this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (this.binding == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            } else {
                InputMethodManager inputMethodManager = (InputMethodManager)binding;
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return true;
            }
        } else {
            return false;
        }
    }
}
