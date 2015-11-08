package com.cxy229.weather5;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cxy229.weather5.domain.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText editText;
	TextView textView;
	Button button;
	public String date = new String();
	public String currentCity = new String();
	public String uri = new String();
	public String city = new String();
	public String s = new String();
	public HttpGet httpGet;
	public HttpClient httpClient;
	public HttpEntity httpEntity;
	public HttpResponse httpResponse;
	public static Weather weather;
//	MyTask task ;
//	ProgressDialog dialog = new ProgressDialog(this);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }
		editText = (EditText)findViewById(R.id.editText1Id);
		textView = (TextView)findViewById(R.id.textView1Id);
		button = (Button)findViewById(R.id.button1Id);
//		task = new MyTask();
		button.setOnClickListener(new ButtonListener());
		
		
	}
	
	private class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			city = editText.getText().toString();
			city = RemoveSpace(city);
			uri = "http://api.map.baidu.com/telematics/v3/weather?location=" + city + "&mcode=A5:94:32:06:F8:7A:95:FF:4D:D5:0A:8D:EA:B1:67:8F:83:DC:C9:7F;com.cxy229.weather1&output=json&ak=lzRWG1zoqVgDeSwnQFjrPzK3";
			//textView.setText(getResponse(uri));

			MyTask task = new MyTask(MainActivity.this);
			task.execute(uri);
			//weather = parseJson(getResponse(uri));
			//parseWeather(weather);
		}
		
	}
	
	private String RemoveSpace (String s){
		return s.replace(" ",""); 
	}
	
	private String getResponse(String uri){
		String result = new String();
		httpGet = new HttpGet(uri);
		httpClient = new DefaultHttpClient();
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpEntity = httpResponse.getEntity();
		try {
			result = EntityUtils.toString(httpEntity);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private Weather parseJson(String jsonString){
		Gson gson = new Gson();
		Weather weather1 = gson.fromJson(jsonString,Weather.class);
		return weather1;
	} 
	
	private void parseWeather(Weather weather){  //解析weather
		String s = "当前日期：" + weather.date +"\n"+"error:"+weather.error+"\nstatus:"+weather.status;
		textView.setText(s);
	}

	class MyTask extends AsyncTask<String,Void,Weather >{

		Context myContext;
		ProgressDialog dialog = new ProgressDialog(MainActivity.this);
		
		public MyTask(Context context){
			myContext = context;
		}
		public MyTask() {
			// TODO Auto-generated constructor stub
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.setTitle("提示");
			dialog.setMessage("正在加载，请稍后....");
			dialog.setCancelable(false);
			dialog.show();
			
			
		}

		@Override
		protected Weather doInBackground(String... params) {
			// TODO Auto-generated method stub
			weather = parseJson(getResponse(uri));
			return weather;
		}

		@Override
		protected void onPostExecute(Weather result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			parseWeather(result);
			dialog.dismiss();
			if(weather.error.equals("0")){
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, WeatherPage.class);
			startActivity(intent);
			}
			else
			{
				Toast.makeText(MainActivity.this, weather.message+"  "+weather.status, 2).show();
				
			}
		}

	}
	
	public static Weather getWeather (){
		return weather;
	}
	
//	public static void getdata(){}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
