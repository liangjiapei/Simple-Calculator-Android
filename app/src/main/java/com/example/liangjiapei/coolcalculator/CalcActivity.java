package com.example.liangjiapei.coolcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalcActivity extends Activity {

    TextView resultTextView;

    public enum Operation {
        ADD, SUBTRACT, DIVIDE, MULTIPLY, EQUAL
    }

    String runningNumber = "";
    String leftValueStr = "";
    String rightValueStr = "";

    int previousRightValue = 0;

    int result;

    Operation currentOperation;
    Operation previousOperation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Button oneBtn = (Button) findViewById(R.id.oneBtn);
        Button twoBtn = (Button) findViewById(R.id.twoBtn);
        Button threeBtn = (Button) findViewById(R.id.threeBtn);
        Button fourBtn = (Button) findViewById(R.id.fourBtn);
        Button fiveBtn = (Button) findViewById(R.id.fiveBtn);
        Button sixBtn = (Button) findViewById(R.id.sixBtn);
        Button sevenBtn = (Button) findViewById(R.id.sevenBtn);
        Button eightBtn = (Button) findViewById(R.id.eightBtn);
        Button nineBtn = (Button) findViewById(R.id.nineBtn);
        Button zeroBtn = (Button) findViewById(R.id.zeroBtn);

        ImageButton divideBtn = (ImageButton) findViewById(R.id.divideBtn);
        ImageButton multiplyBtn = (ImageButton) findViewById(R.id.multiplyBtn);
        ImageButton subtractBtn = (ImageButton) findViewById(R.id.subtractBtn);
        ImageButton addBtn = (ImageButton) findViewById(R.id.addBtn);
        ImageButton calcBtn = (ImageButton) findViewById(R.id.calcBtn);

        Button clearBtn = (Button) findViewById(R.id.clearBtn);

        resultTextView = (TextView) findViewById(R.id.resultTextView);

        resultTextView.setText("");

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(1);
            }
        });

        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(2);
            }
        });

        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(3);
            }
        });

        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(4);
            }
        });

        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(5);
            }
        });

        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(6);
            }
        });

        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(7);
            }
        });

        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(8);
            }
        });

        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(9);
            }
        });

        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(0);
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.DIVIDE);
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.MULTIPLY);
            }
        });

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.SUBTRACT);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.ADD);
            }
        });

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.EQUAL);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runningNumber = "";
                result = 0;
                leftValueStr = "";
                rightValueStr = "";
                resultTextView.setText(runningNumber);
                currentOperation = null;
                previousOperation = null;
                previousRightValue = 0;
            }
        });


    }

    void processOperation(Operation operation) {
        if (currentOperation != null) {

            if (runningNumber.length() != 0) {
                rightValueStr = runningNumber;

                switch (currentOperation) {
                    case ADD:
                        if (leftValueStr.equals("Error")) {
                            resultTextView.setText(leftValueStr);
                        } else {
                            result = Integer.parseInt(leftValueStr) + Integer.parseInt(rightValueStr);
                            leftValueStr = String.valueOf(result);
                            previousOperation = Operation.ADD;
                            previousRightValue = Integer.parseInt(rightValueStr);
                            Log.d("previous operation is", "ADD");
                        }

                        break;
                    case SUBTRACT:
                        if (leftValueStr.equals("Error")) {
                            resultTextView.setText(leftValueStr);
                        } else {
                            result = Integer.parseInt(leftValueStr) - Integer.parseInt(rightValueStr);
                            leftValueStr = String.valueOf(result);
                            previousOperation = Operation.SUBTRACT;
                            previousRightValue = Integer.parseInt(rightValueStr);
                            Log.d("previous operation is", "SUBTRACT");
                        }
                        break;
                    case MULTIPLY:
                        if (leftValueStr.equals("Error")) {
                            resultTextView.setText(leftValueStr);
                        } else {
                            result = Integer.parseInt(leftValueStr) * Integer.parseInt(rightValueStr);
                            leftValueStr = String.valueOf(result);
                            previousOperation = Operation.MULTIPLY;
                            previousRightValue = Integer.parseInt(rightValueStr);
                            Log.d("previous operation is", "MULTIPLY");
                        }
                        break;
                    case DIVIDE:
                        if (rightValueStr.equals("0")) {
                            leftValueStr = "Error";
                        } else {
                            if (leftValueStr.equals("Error")) {
                                resultTextView.setText(leftValueStr);
                            } else {
                                result = Integer.parseInt(leftValueStr) / Integer.parseInt(rightValueStr);
                                leftValueStr = String.valueOf(result);
                                previousRightValue = Integer.parseInt(rightValueStr);
                            }
                        }
                        previousOperation = Operation.DIVIDE;
                        Log.d("previous operation is", "DIVIDE");
                        break;
                    case EQUAL:
                        leftValueStr = runningNumber;
                        Log.d("previous operation is", "EQUAL");
                        break;
                }

                resultTextView.setText(leftValueStr);

            } else {
                if (previousOperation != null && operation == Operation.EQUAL) {
                    switch (previousOperation) {
                        case ADD:
                            result = Integer.parseInt(leftValueStr) + previousRightValue;
                            leftValueStr = String.valueOf(result);

                            break;
                        case SUBTRACT:
                            result = Integer.parseInt(leftValueStr) - previousRightValue;
                            leftValueStr = String.valueOf(result);
                            break;
                        case MULTIPLY:
                            result = Integer.parseInt(leftValueStr) * previousRightValue;
                            leftValueStr = String.valueOf(result);
                            break;
                        case DIVIDE:
                            if (rightValueStr.equals("0")) {
                                leftValueStr = "Error";
                            } else {
                                result = Integer.parseInt(leftValueStr) / previousRightValue;
                                leftValueStr = String.valueOf(result);
                            }
                            break;
                        case EQUAL:
                            leftValueStr = runningNumber;
                            Log.d("pressing equal again", "=");
                    }
                    resultTextView.setText(leftValueStr);
                }
            }

        } else {
            leftValueStr = runningNumber;
            runningNumber = "";

        }

        currentOperation = operation;
        runningNumber = "";
    }

    void numberPressed(int number) {
        if (runningNumber.equals("0")) {
            runningNumber = "";
        }
        runningNumber += String.valueOf(number);
        resultTextView.setText(runningNumber);
    }
}
