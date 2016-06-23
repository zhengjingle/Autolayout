package com.autolayout.demo;

import com.zjl.autolayout.AutoUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * @author ZhengJingle
 *
 */
public class ListViewActivity extends Activity {

	RecyclerView recyclerView;
	MyAdapter adapter;

	String[] datas = {
			"评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论",
			"好", "非常好",
			"android:bufferType    指定getText()方式取得的文本类别。选项editable 类似于StringBuilder可追加字符，也就是说getText后可调用append方法设置文本内容。spannable 则可在给定的字符区域使用样式。  android:capitalize    设置英文字母大写类型。此处无效果，需要弹出输入法才能看得到，参见EditView此属性说明。 android:cursorVisible    设定光标为显示/隐藏，默认显示。 android:digits    设置允许输入哪些字符。如“1234567890.+-*/% ()” "
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		AutoUtils.auto(this);//适配实际屏幕
		
		
		recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView1);
		recyclerView.setAdapter(adapter = new MyAdapter());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	private class MyAdapter extends
			RecyclerView.Adapter<MyAdapter.MyViewHolder> {

		@Override
		public int getItemCount() {
			return datas.length;
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			holder.tv_comment.setText(datas[position]);
		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			// TODO 自动生成的方法存根
			View view = View.inflate(ListViewActivity.this, R.layout.list_item, null);

			MyViewHolder viewHolder = new MyViewHolder(view);

			return viewHolder;
		}

		class MyViewHolder extends ViewHolder {
			public View view;
			public TextView tv_name, tv_comment, tv_like, tv_ding;
			public ImageView iv_head;

			public MyViewHolder(View view) {
				super(view);
				this.view = view;

				tv_name = (TextView) view.findViewById(R.id.textView1);
				tv_comment = (TextView) view.findViewById(R.id.textView2);
				tv_like = (TextView) view.findViewById(R.id.textView3);
				tv_ding = (TextView) view.findViewById(R.id.textView4);
				iv_head = (ImageView) view.findViewById(R.id.imageView2);

				AutoUtils.auto(view);//列表适配实际屏幕
			}
		}

	}

}
