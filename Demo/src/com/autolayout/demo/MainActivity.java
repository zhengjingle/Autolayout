package com.autolayout.demo;

import com.zjl.autolayout.AutoUtils;

import android.app.Activity;
import android.os.Bundle;
/**
 * 
 * @author ZhengJingle
 *
 */
public class MainActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		AutoUtils.auto(this);//适配实际屏幕
		
	}

	
}
