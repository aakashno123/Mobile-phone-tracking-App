package com.example.project;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity implements AnimationListener {
	Animation animFadeOut,animfadein;
	ImageView iv;
	TextView tv;
	private boolean bbp;
	private static final int holding=3000;
	private Handler h;
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_splash);
	iv=(ImageView)findViewById(R.id.imageView1);
	tv=(TextView)findViewById(R.id.textView1);
	animFadeOut=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
	animfadein=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.together);
	animFadeOut.setAnimationListener(this);
	animfadein.setAnimationListener(this);
	iv.startAnimation(animfadein);
	tv.startAnimation(animFadeOut);
	

	h= new Handler();
	h.postDelayed(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			finish();
			if(!bbp)
			{
				Intent intent =new Intent(Splash.this,MainActivity.class);
				Splash.this.startActivity(intent);
				
			}
			
			
			
			
		}
	}, holding);
	 
	 }
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	bbp=true;
	super.onBackPressed();

}
@Override
public void onAnimationStart(Animation animation) {
	// TODO Auto-generated method stub
	
}
@Override
public void onAnimationEnd(Animation animation) {
	// TODO Auto-generated method stub
	if(animation==animFadeOut)
	{
		tv.startAnimation(animFadeOut);
	}
	
}
@Override
public void onAnimationRepeat(Animation animation) {
	// TODO Auto-generated method stub
	
}
}



	