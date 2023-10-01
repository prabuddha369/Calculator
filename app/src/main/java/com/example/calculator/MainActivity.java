package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.splashscreen.SplashScreen;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    //Variable declaration
    Button one, two, three, four, five, six, seven, eight, nine, zero, point, add, div, sub, mul, C, percent, equal;
    View erase;
    TextView answer;
    Boolean flg = false,flag=true;//flg helps if the user has done his previous calculation and is wanting to start a new calculation and flag helps to detect 2.2.3.4 type of input
    Toast t1,t2;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);//adding splash screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the Notification Bar color to black
        getWindow().setStatusBarColor(Color.parseColor("#000000"));
        //Setting the ButtonClick Animation
        Animation clickAnimation = AnimationUtils.loadAnimation(this, R.anim.buttonclick);
        //Setting the Error Toast Message
        t1=Toast.makeText(MainActivity.this,"Invalid Format", Toast.LENGTH_SHORT);
        t2=Toast.makeText(MainActivity.this,"Maximum character limit reached (24 characters)", Toast.LENGTH_SHORT);

        //Initializing UI elements
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        C = findViewById(R.id.C);
        point = findViewById(R.id.point);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.multi);
        div = findViewById(R.id.div);
        erase = findViewById(R.id.erase);
        percent = findViewById(R.id.percent);
        equal = findViewById(R.id.equal);
        answer = findViewById(R.id.answer);
        toggleButton = findViewById(R.id.toggle);

        //Getting the user device theme and setting the app theme accordingly
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        toggleButton.setChecked(currentNightMode == Configuration.UI_MODE_NIGHT_NO);

        //setting toggle button to switch between day and night theme
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the state change here and update the night mode setting
                if (isChecked) {
                    // Set night mode to light
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    // Set night mode to dark
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });

        // Setting all the Number buttons
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);
                handleNumberClick("1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleNumberClick("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleNumberClick("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);
                handleNumberClick("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleNumberClick("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleNumberClick("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleNumberClick("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleNumberClick("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleNumberClick("9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleNumberClick("0");
            }
        });

        //Setting the Decimal Button
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handlePointClick();
            }
        });

        //Setting the percentage button
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handlePercentClick();
            }
        });

        //Setting Addition button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleOperatorClick("+");
            }
        });

        //Setting Substraction Button
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleOperatorClick("-");
            }
        });

        //Setting the Multiply button
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleOperatorClick("×");
            }
        });

        //Setting the Divide button
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleOperatorClick("÷");
            }
        });

        //Setting the Equal button
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleEqualClick();
            }
        });

        //Setting the Clear ALL button
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);//Changing the TextView Size to normal if there was a change in size due to large calculations
                v.startAnimation(clickAnimation);handleClearClick();
            }
        });

        //Setting the Erase button
        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(clickAnimation);handleEraseClick();
            }
        });
    }

    //Number click function to concat the respective number to the back of the String
    private void handleNumberClick(String number) {
        if (flg) {
            answer.setText("");
            flg = false;
        }
        String currentText = answer.getText().toString();
        if(!currentText.isEmpty())
        {
            if(currentText.charAt(currentText.length() - 1)=='%')
            {
                answer.setText(currentText +"×"+number);
                return;
            }
        }
        if (currentText.length() >= 12) {
            answer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        } else {
            answer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        }
        if (currentText.length() < 24) {
            answer.setText(currentText + number);
        } else {
            t1.cancel();
            t2.cancel();
            t2.show();
        }
    }

    private void handlePointClick() {
        if (flg) {
            answer.setText("");
            flg = false;
        }
        String currentText = answer.getText().toString();
        if (flag){
            answer.setText(currentText + ".");
            flag=false;
        }
        else {
            t1.cancel();
            t2.cancel();
            t1.show();
            return;
        }

        if (currentText.isEmpty()) {
            answer.setText("0.");//Just to look good
        } else if (isOperator(currentText.charAt(currentText.length() - 1))) {
            if(currentText.charAt(currentText.length() - 1)=='.')
            {
                t1.cancel();
                t2.cancel();
                t1.show();
            }
            else{
                answer.setText(currentText + "0.");
            }
        }
    }

    private void handlePercentClick() {
        String currentText = answer.getText().toString();
        if (currentText.isEmpty() || isOperator(currentText.charAt(currentText.length() - 1))) {
            t1.cancel();
            t2.cancel();
            t1.show();
        } else {
            answer.setText(currentText +"%");
        }
        flag=true;
    }

    private void handleOperatorClick(String operator) {
        String currentText = answer.getText().toString();
        flag=true;
        if (currentText.isEmpty() || isOperator(currentText.charAt(currentText.length() - 1))) {
            t1.cancel();
            t2.cancel();
            t1.show();
        } else {
            answer.setText(currentText + operator);
        }
    }

    private boolean isOperator(char character) {
        return character == '+' || character == '-' || character == '×' || character == '÷' || character == '%'|| character == '.';
    }

    //Calculating the result when the equal is clicked
    private void handleEqualClick() {
        if (answer.getText().toString().isEmpty()) {//To check if the text is empty since it cause exception
            return;
        } else {//Calculating result
            String expression = answer.getText().toString();//Getting the equation eg: 5+4-6÷5×8.8%
            expression = expression.replace("÷", "/");//changing the operator to avoid exception
            expression = expression.replace("×", "*");//changing the operator to avoid exception
            expression = expression.replace("%", "/100");//changing the operator to avoid exception

            try {//Using try catch to get Arithmetic exception like divide by 0
                Expression exp = new ExpressionBuilder(expression).build();
                double result = exp.evaluate();//Evaluating by converting from string to numbers using ExpressionBuilder
                String currentTextr = String.valueOf(result);//Setting the result on screen

                //Changing the TextViw size and max char limit according to the result size
                int currentTextSize = (int) answer.getTextSize();
                int maxCharLimit = 11;
                if (currentTextSize == getResources().getDimensionPixelSize(R.dimen.text_size_50sp)) {
                    if (currentTextr.length() >= maxCharLimit) {
                        answer.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                    }
                }

                //avoiding unnessary .0 at the back of result
                if (result % 1 == 0 && result<=9223372036854775807.0) {
                    answer.setText(String.valueOf((long) result)); // Display as an integer if no fractional part
                } else {
                    answer.setText(String.valueOf(result)); // Display with decimals if there's a fractional part
                }

                flg = true;//Setting the Flg to true to avoid concatination of next calculation numbers to the result
                answer.setFilters(new InputFilter[]{new InputFilter.LengthFilter(24)});//setting the default max char lenght of TextView
            } catch (ArithmeticException e) {
                answer.setText("Error");//Divide by zero
            }
        }
        flag=true;
    }

    private void handleClearClick() {
        answer.setText("");
        flag=true;
    }//Clear the TextView

    private void handleEraseClick() {//Erasing the last value
        String currentText = answer.getText().toString();
        if (!currentText.isEmpty()) {
            String newText = currentText.substring(0, currentText.length() - 1);
            if(currentText.charAt(currentText.length() - 1)=='.')
            {
                flag=true;
            }
            answer.setText(newText);
        }
    }
}
