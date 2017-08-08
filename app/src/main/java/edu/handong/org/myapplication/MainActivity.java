package edu.handong.org.myapplication;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private long mStartTime, mEndTime;
    private BusStationXMLParser mXMLParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {
                // TODO Auto-generated method stub
                mStartTime = System.currentTimeMillis();
                mXMLParser = new BusStationXMLParser("http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getCrdntPrxmtSttnList?ServiceKey=Utk4pE8vGB%2BaPGsKKwhKQreMwU2cR4K9Dxc7t%2BjJEdgetcgm3E1UGZhdeYDxTSzaydt5a8SvvDdEhNNDbDr7HA%3D%3D&gpsLati=36.039589&gpsLong=129.366269&numOfRows=999&pageSize=999&pageNo=1&startPage=1", mHandler);
                Thread thread = new Thread(mXMLParser);
                thread.start();
            }
        });
    }

    public void startLocationService() {
        long minTime = 10000;
        float minDistance = 0;
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Double latitude = location.getLatitude();
                        Double longitude = location.getLongitude();
                        Log.d("Data List Size", String.valueOf(latitude + longitude));
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                }
        );

    }

    Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            mEndTime = System.currentTimeMillis();
            Log.d("Taken Time", Long.toString((mEndTime - mStartTime) / 1000L));
            ArrayList<BusStationDatas> dataList = mXMLParser.getResult();
            int dataListSize = dataList.size();
            Log.d("Data List Size", Integer.toString(dataListSize));
            for(int i = 0; i < dataListSize; i++){
                Log.d("XML Parsing Result", dataList.get(i).getBusID()+ " >>> " + "Station Lati : " + dataList.get(i).getBusStationLati() + " >>> " + "Station Longi : " + dataList.get(i).getBusStationLongi());
            }
        }
    };



 }