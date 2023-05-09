package sg.edu.rp.c346.id22024713.billplease;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText amountInput;
    EditText paxInput;
    EditText discountInput;
    ToggleButton svs;
    ToggleButton gst;
    Button splitButton;
    Button resetButton;
    TextView eachPaysText;
    TextView totalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountInput = findViewById(R.id.amountInputId);
        paxInput = findViewById(R.id.paxInputId);
        eachPaysText = findViewById(R.id.eachPays);
        totalBill = findViewById(R.id.totalBill);
        discountInput = findViewById(R.id.discountInput);
        svs = findViewById(R.id.toggleButton);
        gst = findViewById(R.id.toggleButton2);
        splitButton = findViewById(R.id.split);
        resetButton = findViewById(R.id.reset);

        splitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountString = amountInput.getText().toString();
                double amountInput = Integer.parseInt(amountString);
                String paxString = paxInput.getText().toString();
                double paxInput = Integer.parseInt(paxString);
                String discountString = discountInput.getText().toString();
                double discountInput = Integer.parseInt(discountString);
                double amountTotal = 0.0;
                double eachPays = 0.0;

                if (svs.isChecked() && gst.isChecked()) {
                    amountTotal = amountInput * 1.18;
                } else if (svs.isChecked() && !gst.isChecked()) {
                    amountTotal = amountInput * 1.1;
                } else if (!svs.isChecked() && gst.isChecked()) {
                    amountTotal = amountInput * 1.08;
                } else if (!svs.isChecked() && !gst.isChecked()) {
                    amountTotal = amountInput;
                }
                if (discountInput != 0) {
                    amountTotal *= 1 - (discountInput/100);
                }
                eachPays = amountTotal / paxInput;
                String eachPaysString = String.format("Each pays: $%.2f", eachPays);
                String amountTotalString = String.format("Total amount: $%.2f", amountTotal);
                totalBill.setText(amountTotalString);
                eachPaysText.setText(eachPaysString);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountInput.setText("");
                paxInput.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discountInput.setText("");
            }
        });
    }
}