package com.autolayout.demo;

import com.zjl.autolayout.AutoUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 
 * @author ZhengJingle
 *
 */
public class DemoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		
		AutoUtils.setSize(this, false, 720, 1280);//没有状态栏,设计尺寸的宽高
		
		
		setContentView(R.layout.demo);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				DemoActivity.this.startActivity(new Intent(DemoActivity.this,MainActivity.class));
			}
			
		});
		
		findViewById(R.id.button2).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				DemoActivity.this.startActivity(new Intent(DemoActivity.this,ListViewActivity.class));
			}
			
		});
	}

}
