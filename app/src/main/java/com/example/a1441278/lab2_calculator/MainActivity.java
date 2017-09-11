package com.example.a1441278.lab2_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etLoanAmount, etTermOfLoan, etInterest;
    TextView tvMonthlyPayment, tvTotalPayment, tvTotalInterest, tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLoanAmount = (EditText) findViewById(R.id.etLoanAmount);
        etTermOfLoan = (EditText) findViewById(R.id.etTermOfLoan);
        etInterest = (EditText) findViewById(R.id.etInterest);
        tvMonthlyPayment = (TextView) findViewById(R.id.tvMonthlyPayment);
        tvTotalPayment = (TextView) findViewById(R.id.tvTotalPayment);
        tvTotalInterest = (TextView) findViewById(R.id.tvTotalInterest);
        tvError = (TextView) findViewById(R.id.tvError);
    }

    public void calculate(View v) {
        String strLoanAmt = etLoanAmount.getText().toString();
        String strTermOfLoan = etTermOfLoan.getText().toString();
        String strInterest = etInterest.getText().toString();

        if(validatedInput()){
            double loanAmt = Double.parseDouble(strLoanAmt);
            int numOfYear = Integer.parseInt(strTermOfLoan);
            double yearlyInterest = Double.parseDouble(strInterest);

            LoanCalculator calculator = new LoanCalculator(loanAmt, numOfYear, yearlyInterest);
            tvMonthlyPayment.setText(calculator.getMonthlyPayment() + "");
            tvTotalPayment.setText(calculator.getTotalCostOfLoan() + "");
            tvTotalInterest.setText(calculator.getTotalInterest() + "");
            tvError.setText("");
            tvError.setVisibility(View.INVISIBLE);
        }
    }

    public void clearFields(View v) {
        etLoanAmount.setText("");
        etTermOfLoan.setText("");
        etInterest.setText("");
        tvTotalPayment.setText("");
        tvMonthlyPayment.setText("");
        tvTotalInterest.setText("");
        tvError.setText("");
        tvError.setVisibility(View.INVISIBLE);
    }

    private boolean validatedInput() {
        String loanAmt = etLoanAmount.getText().toString();
        String termOfLoan = etTermOfLoan.getText().toString();
        String interest = etInterest.getText().toString();

        if(loanAmt.isEmpty() || termOfLoan.isEmpty() || interest.isEmpty()) {
            tvError.setText(R.string.errorMissingFields);
            tvError.setVisibility(View.VISIBLE);
            return false;
        }

        try {
            Double.parseDouble(loanAmt);
            Integer.parseInt(termOfLoan);
            Double.parseDouble(interest);
            tvError.setText("");
            tvError.setVisibility(View.INVISIBLE);
        } catch (NumberFormatException e){
            tvError.setText(R.string.errorInvalidNumber);
            tvError.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }
}
