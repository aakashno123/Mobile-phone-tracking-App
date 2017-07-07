package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends Activity {
public static SharedPreferences sharedPreferences;
Button save;
Button cancel;
EditText PhoneNum;
EditText Pass;
public static String num;
public static String pass;
public static final String mypreference = "mypref";
public static final String Number = "numberKey";
public static final String Password = "passKey";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		save=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
		PhoneNum=(EditText)findViewById(R.id.editText1);
		Pass=(EditText)findViewById(R.id.editText2);
		
		
		
		
		sharedPreferences=getSharedPreferences(mypreference, Context.MODE_WORLD_WRITEABLE);
		if (sharedPreferences.contains(Number)) {
            PhoneNum.setText(sharedPreferences.getString(Number, ""));
        }
        if (sharedPreferences.contains(Password)) {
            Pass.setText(sharedPreferences.getString(Password, ""));
 
        }
	
	save.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			num=PhoneNum.getText().toString();
			pass=Pass.getText().toString();
			SharedPreferences.Editor editor= sharedPreferences.edit();
			editor.putString(Number, num);
	        editor.putString(Password, pass);
	        editor.commit();
	        Intent obj=new Intent(Register.this, MainActivity.class);
	        startActivity(obj);
	    }
	});
		
cancel.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent obj=new Intent(Register.this, MainActivity.class);
		startActivity(obj);
	}
});
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
