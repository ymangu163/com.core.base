package com.itheima.smshelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListSmsActivity extends Activity {
	private ListView lv;
	private String[] objects = {
			"玫瑰香香，情人黏黏，情话甜甜，情歌绵绵；花灯灿灿，礼花点点，好运连连，好梦圆圆。情人节喜逢元宵节，喜鹊登枝蝴蝶成双鸳鸯成对双喜临门祝双节快乐，合家团团圆圆，甜甜蜜蜜，开开心心，幸幸福福",
			"情人节快到了，我精心挑选玫瑰花、百合花和满天星，扎成一束鲜花随短信送给你，火红的玫瑰代表热烈奔放，洁白的百合代表百年好合，小小的满天星代表幸福美好。愿你的情人节热烈奔放，你们的爱情百年好合，你们的生活幸福美满。预祝情人节快乐",
			"^o^满天星光，最爱你许过愿望的那一颗，鲜花绽放，最爱你摘下微笑的那一朵，曼妙旋律，最爱你感动落泪的那一段，亲爱的，情人节快乐，爱你。 ",
			"^o^宝贝，情人节到了，送你一束玫瑰，用真心塑料纸包扎，系上快乐彩带，喷点爱的香水，插一张真情卡片，写着：宝贝，愿我的爱能带给你一生的快乐！" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.sms_item,
				R.id.tv_info, objects));
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String smsinfo = objects[position];
				Intent data = new Intent();
				data.putExtra("smsinfo", smsinfo);
				//设置数据。
				setResult(0, data);
				//关闭掉当前的activity，并且回传数据 onActivityResult().
				finish();
			}
		});
	}
}
