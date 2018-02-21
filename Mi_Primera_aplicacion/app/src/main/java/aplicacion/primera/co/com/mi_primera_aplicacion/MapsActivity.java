package aplicacion.primera.co.com.mi_primera_aplicacion;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Marker marcador;
    private Location myLocation;
    double latitud = 0.0;
    double longitud = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0),16));
        miUbicacion();


        agregarMarcador(6.256027, -75.578504);
        agregarMarcador(6.255543, -75.580561);
        agregarMarcador(6.254502, -75.581987);

        mMap.setOnInfoWindowClickListener(this);

    }


    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);


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
    };





    private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myLocation= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(myLocation);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0,locListener);

    }



    public void agregarMarcador(double lat, double lon) {

        Marker marker= mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lon))
                .title("Barberia Olimpo")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
        );

        onInfoWindowClick(marker);


    }


    private void actualizarUbicacion(Location loc) {
        if (loc != null) {

            latitud = loc.getLatitude();
            longitud = loc.getLongitude();
            Log.i("","Coordenadas mi posicion latitud: "+latitud+" longitu: "+longitud);
            LatLng coordenadas = new LatLng(latitud, longitud);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadas));
        }
    }

    public float calcularDistancia(double lat, double lon){
        Location locationB = new Location("punto B");

        locationB.setLatitude(lat);
        locationB.setLongitude(lon);
        return  myLocation.distanceTo(locationB);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        marker.setSnippet("Se encuentra a: " + (((int) calcularDistancia(marker.getPosition().latitude,marker.getPosition().longitude))+" metros"));

    }
}
