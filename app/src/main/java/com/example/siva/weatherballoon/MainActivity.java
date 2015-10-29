package com.example.siva.weatherballoon;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements LocationListener {

    ToggleButton serviceOn, locationOn;
    TextView latitudeDisp, longitudeDisp, altitudeDisp;
    LocationManager locationManager;
    boolean sendData = false;
    boolean updateLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceOn = (ToggleButton) findViewById(R.id.sendService);
        locationOn = (ToggleButton) findViewById(R.id.LocationOn);
        latitudeDisp = (TextView) findViewById(R.id.latitude);
        latitudeDisp.setText("0.0");
        longitudeDisp = (TextView) findViewById(R.id.longitude);
        longitudeDisp.setText("0.0");
        altitudeDisp = (TextView) findViewById(R.id.altitude);
        altitudeDisp.setText("0.0");
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300, 10, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("onLocationChanged", "entered");
        if (updateLocation) {
        String latitude = "" + location.getLatitude();
        latitudeDisp.setText(latitude);
        String longitude = "" + location.getLongitude();
        longitudeDisp.setText(longitude);
        String altitude = "" + location.getAltitude();
        altitudeDisp.setText(altitude);
            if (sendData) {
                /* Will send the SMS here*/
            }
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

        /******** Called when User off Gps *********/

        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        /******** Called when User on Gps  *********/

        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

}
