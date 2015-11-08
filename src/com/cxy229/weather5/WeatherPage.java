package com.cxy229.weather5;

import com.cxy229.weather5.domain.Weather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

public class WeatherPage extends Activity{
	TextView textViewCurrentCity;
	TextView textViewDate0;
	TextView textViewWeatherData0;
	Weather weather ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_page);
		textViewCurrentCity = (TextView)findViewById(R.id.textViewCurrentCity);
		textViewDate0 = (TextView)findViewById(R.id.textViewDate0);
		textViewWeatherData0 = (TextView)findViewById(R.id.textViewWeatherData0);
		weather = MainActivity.getWeather();
		
		textViewCurrentCity.setText(weather.results.get(0).currentCity);
		textViewDate0.setText(weather.results.get(0).weather_data.get(0).date);
		textViewWeatherData0.setText(weather.results.get(0).weather_data.get(0).weather +"\n"+weather.results.get(0).weather_data.get(0).wind + "\npm2.5 : "+weather.results.get(0).pm25);
		
		ExpandableListAdapter adapter0 = new BaseExpandableListAdapter() {
			private String[] groups = new String[]{
					weather.results.get(0).index.get(0).title+"   "+weather.results.get(0).index.get(0).zs,
					weather.results.get(0).index.get(1).title+"   "+weather.results.get(0).index.get(1).zs,
					weather.results.get(0).index.get(2).title+"   "+weather.results.get(0).index.get(2).zs,
					weather.results.get(0).index.get(3).title+"   "+weather.results.get(0).index.get(3).zs,
					weather.results.get(0).index.get(4).title+"   "+weather.results.get(0).index.get(4).zs,
					weather.results.get(0).index.get(5).title+"   "+weather.results.get(0).index.get(5).zs,
					weather.results.get(0).weather_data.get(1).date+"   "+weather.results.get(0).weather_data.get(1).weather,
					weather.results.get(0).weather_data.get(2).date+"   "+weather.results.get(0).weather_data.get(2).weather,
					weather.results.get(0).weather_data.get(3).date+"   "+weather.results.get(0).weather_data.get(3).weather
			};
			private String[] children = new String[]{
					weather.results.get(0).index.get(0).tipt+"   "+weather.results.get(0).index.get(0).des,
					weather.results.get(0).index.get(1).tipt+"   "+weather.results.get(0).index.get(1).des,
					weather.results.get(0).index.get(2).tipt+"   "+weather.results.get(0).index.get(2).des,
					weather.results.get(0).index.get(3).tipt+"   "+weather.results.get(0).index.get(3).des,
					weather.results.get(0).index.get(4).tipt+"   "+weather.results.get(0).index.get(4).des,
					weather.results.get(0).index.get(5).tipt+"   "+weather.results.get(0).index.get(5).des,
					weather.results.get(0).weather_data.get(1).wind+"   "+weather.results.get(0).weather_data.get(1).temperature,
					weather.results.get(0).weather_data.get(2).wind+"   "+weather.results.get(0).weather_data.get(2).temperature,
					weather.results.get(0).weather_data.get(3).wind+"   "+weather.results.get(0).weather_data.get(3).temperature,
			};
			
			private TextView getTextView (){
				
				TextView textView = new TextView(WeatherPage.this);
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT); 
				textView.setLayoutParams(lp);
				textView.setShadowLayer(2, 4, 4, 495);
				textView.setTextSize(20);
				textView.setPadding(90,10, 10, 10);
				
				return textView;
			}
			
			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView textView = getTextView();
				textView.setText(getGroup(groupPosition).toString());
				return textView;
			}
			
			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}
			
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return groups.length;
			}
			
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return groups[groupPosition];
			}
			
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return 1;
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView textView = getTextView();
				textView.setText(children[groupPosition].toString());
				return textView;
			}
			
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return children[groupPosition];
			}
		};
		
//		String weatherData[] = {
//				weather.results.get(0).weather_data.get(1).date+"   "+weather.results.get(0).weather_data.get(1).weather + "   "+weather.results.get(0).weather_data.get(1).wind+"   "+weather.results.get(0).weather_data.get(1).temperature,
//				weather.results.get(0).weather_data.get(2).date+"   "+weather.results.get(0).weather_data.get(2).weather + "   "+weather.results.get(0).weather_data.get(2).wind+"   "+weather.results.get(0).weather_data.get(2).temperature,
//				weather.results.get(0).weather_data.get(3).date+"   "+weather.results.get(0).weather_data.get(3).weather + "   "+weather.results.get(0).weather_data.get(3).wind+"   "+weather.results.get(0).weather_data.get(3).temperature
//		};
//		
//		ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.array_item, weatherData);
//		
//		ListView listView1;
//		listView1 = (ListView)findViewById(R.id.ListView1);
//		listView1.setAdapter(adapter1);
		ExpandableListView elistView0;
		elistView0 = (ExpandableListView)findViewById(R.id.expandableListView0);
		elistView0.setAdapter(adapter0);
		
	}

}
