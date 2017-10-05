package com.example.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
                                                                //วิธี2 V เพิ่ม implements View.OnClickListener
public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/{
    //ปรกาศตัวแปรView
    private EditText mHeightEditText,mWrightEditText;
    private Button mCalculateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeightEditText = (EditText) findViewById(R.id.height_edit_text);
        mWrightEditText = (EditText) findViewById(R.id.weight_edit_text); //fbc
        mCalculateButton = (Button) findViewById(R.id.calculate_button);

//วิธี 1 สร้างคลาสออกมาเป็นอินเนอคลาส แล้ว im และonCในคลาสอินเนอ
      /*  MyListener listener = new MyListener(); //สร้าง obj ที่ทำหน้าที่เป็น Listener ของปุ่ม
        mCalculateButton.setOnClickListener(listener); //กำหนก obj ที่เป็น Listener ให้กับปุ่ม
    */

//วิธี2 ให้คลาสเป็น ริสเซอเนอด้วยเลย
        //ใช้thisใส่เข้าไปเลย เพราะอยู่ในคลาสMainActivityอยู่แล้ว หริแ หรือประกาศ  MainActivity listener = new MainActivity();
      /*  mCalculateButton.setOnClickListener(this);*/
//วิธี3 คลาสแบบไม่มีชื่อ new View.OnClickListener() ที่ทำแบบนี้ได้เพราะคอมไฟเลอสร้างคลาสให้แต่ไม่มีชื่อแล้วไปเรียน View.OnClickListener()
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Toast t = Toast.makeText(
                        MainActivity.this,//ถ้า this อย่างเดียวจะหมายถึงส่วนฟ้าหรือ     new View.OnClickListener()
                        "Hello",
                        Toast.LENGTH_LONG
                         );*/

                String heightText = mHeightEditText.getText().toString();//*** การอ่านค่าจาก edit text รีเทีนมาเป็น CharSequrnce จึง .toString()
                Double height = Double.valueOf(heightText);

                Double weight = Double.valueOf(mWrightEditText.getText().toString());
                final Double bmi = weight / ((height/100)*(height/100));
                /*Toast t =Toast.makeText(
                        MainActivity.this,
                        "ต่า BMI ที่ได้คือ "+String.valueOf(bmi),
                        Toast.LENGTH_SHORT

                );
                t.show();*/

                final String bmiText = getBmiText(bmi);

                //String result = String.format("ต่า BMI ที่ได้คือ %.1f \n\n อยู่ในเกณฑ์ : %s",bmi,bmiText);

                //ให้ไปหน้าถัดไป
                Intent intent = new Intent(MainActivity.this,BmiResultActivity.class);//(context.คลาสปลายทางที่จะรัน)
                intent.putExtra("bmi_value",bmi); //ส่งค่าไป (key,value)
                intent.putExtra("bmi_text",bmiText); //ส่งค่าไปแอคทิวิตีปลายทาง ดูบรรทัด14 BmiResuitActivity.java
                startActivity(intent);

                /*สร้าง ไดอารอค ตัวแสดงผล*/
                /*AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("BMI Result");
                dialog.setMessage(result);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //โค้ดที่ต้องการให้ทำงาน เมือปุ่ม OK ใน dialog ถูกคลิค
                        //finish(); //เมือกดปุ่ม ok ปิดแอคทิวิตีปัจจุบัน เลย
                        //mHeightEditText.setText(""); //เคลียข้อมูล
                        //mWrightEditText.setText("");
                        //mHeightEditText.requestFocus();//ให้เคอเซอกลับไปช่องแรก



                    }
                });//+p ปุ่มเชิงยอมรับ yes    -ne เชิงปฎิเสธ เช่นปุ่ม no  newนอว กลาง ไม่สนใจ
                //setPositiveButton(ข้อความ,ลิเซอเนอที่อยากให้ทำ);

                dialog.show();*/



            }
        });
    }
    private String getBmiText(Double bmi) {
        /*
                bmi <18.5 นนน้อยกว่าปกติ
                18.5<=bmi<25 ปกติ
                25 <= bmi<30 มากกว่าปกติ (ท้วม)
                bmi >= 30 มากกว่าปกติ(อ้วน)
        */
        String bmiText = "";
        if (bmi < 18.5){
            bmiText = "น้ำหนักน้อยกว่าปกติ";
        }else if(bmi<25){
            bmiText = "น้ำหนักปกติ";
        }else if(bmi<30){
            bmiText = "น้ำหนักมากกว่าปกติ (ท้วม)";
        }else {
            bmiText = "น้ำหนักมากกว่าปกติมาก (อ้วน)";
        }
        return bmiText;
    }
//วิธี2
  /*  public void onClick(View view) {

    }
*/
//วิธี 1

    /*private class MyListener implements View.OnClickListener{ //class ชั้นใน ต้อง implements View.OnClickListener
        @Override
        public void onClick(View view) { //โค้ดที่ทำงานเมือปุ่มถูกคลิก
            Toast t = Toast.makeText(
                    MainActivity.this,//context ถ้าใช้ this จะหมายถึง MyListener MainActivity.thisเพือหมายถึง MainActivity
                    "Hello",//ข้อความที่จะแสดงใน Toast
                    Toast.LENGTH_SHORT //ระยะเวลา
                    );
            t.show();//show Toast (ข้อความแถบดำที่ขึ้นมาแปปเดียว)
        }
*/

    //}
}//end class MainActivity


//ข้อสอบใช้วิธีไหนก็ได้ จะบอกว่าให้คลิกปุ่มแล้วทำไร
//แสดงผลลัพในtextView ใต้ปุ่ม