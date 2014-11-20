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
			"õ�����㣬�����𤣬�黰����������ࣻ���ƲӲӣ��񻨵�㣬��������������ԲԲ�����˽�ϲ��Ԫ���ڣ�ϲȵ��֦������˫ԧ��ɶ�˫ϲ����ף˫�ڿ��֣��ϼ�����ԲԲ���������ۣ��������ģ����Ҹ���",
			"���˽ڿ쵽�ˣ��Ҿ�����ѡõ�廨���ٺϻ��������ǣ�����һ���ʻ�������͸��㣬����õ��������ұ��ţ���׵İٺϴ������úϣ�СС�������Ǵ����Ҹ����á�Ը������˽����ұ��ţ����ǵİ������úϣ����ǵ������Ҹ�������Ԥף���˽ڿ���",
			"^o^�����ǹ⣬������Ը������һ�ţ��ʻ����ţ����ժ��΢Ц����һ�䣬�������ɣ����ж��������һ�Σ��װ��ģ����˽ڿ��֣����㡣 ",
			"^o^���������˽ڵ��ˣ�����һ��õ�壬����������ֽ������ϵ�Ͽ��ֲʴ�����㰮����ˮ����һ�����鿨Ƭ��д�ţ�������Ը�ҵİ��ܴ�����һ���Ŀ��֣�" };

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
				//�������ݡ�
				setResult(0, data);
				//�رյ���ǰ��activity�����һش����� onActivityResult().
				finish();
			}
		});
	}
}
