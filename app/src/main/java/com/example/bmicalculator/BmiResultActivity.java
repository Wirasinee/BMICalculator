package com.example.bmicalculator;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BmiResultActivity extends AppCompatActivity {
    ImageView showImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);
        TextView showOutput = (TextView) findViewById(R.id.showOutput);
         showImage = (ImageView) findViewById(R.id.showImage);
        Intent intent = getIntent();
        Double bmi = intent.getDoubleExtra("bmi_value",0); //(keyต้องตรงกัน,ค่าดีฟอล)
        String bmiText = intent.getStringExtra("bmi_text");
        String result = String.format("ต่า BMI ที่ได้คือ %.1f \n\n อยู่ในเกณฑ์ : %s",bmi,bmiText);
        showOutput.setText(result);
        getBmiIm(bmi);


        //เอาผลลัพไดอารอคมาแสดงกลางจอ
    }
    private void getBmiIm(Double bmi) {
        /*
                bmi <18.5 นนน้อยกว่าปกติ
                18.5<=bmi<25 ปกติ
                25 <= bmi<30 มากกว่าปกติ (ท้วม)
                bmi >= 30 มากกว่าปกติ(อ้วน)
        */

        if (bmi < 18.5){
            showImage.setImageResource(R.drawable.bmi_underweight);
        }else if(bmi<25){
            showImage.setImageResource(R.drawable.bmi_normal_weight);
        }else if(bmi<30){
            showImage.setImageResource(R.drawable.bmi_overweight);
        }else {
            showImage.setImageResource(R.drawable.bmi_obesity);
        }

    }
}


