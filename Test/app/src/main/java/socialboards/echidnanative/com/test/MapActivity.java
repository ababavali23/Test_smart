package socialboards.echidnanative.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import socialboards.echidnanative.com.test.LoginScreen.LoginContract;
import socialboards.echidnanative.com.test.LoginScreen.LoginPresenter;
import socialboards.echidnanative.com.test.model.Listings;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback, LoginContract.MapView {

    private LoginPresenter loginPresenter;
    List<Listings> location = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        loginPresenter = new LoginPresenter(this);
        loginPresenter.mapService();


    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.get(0).getLatitude(), location.get(0).getLongitude()), 5));

        for (int i = 0; i < location.size(); i++) {
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(location.get(i).getLatitude(), location.get(i).getLongitude())));
        }
    }

    @Override
    public void makeToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mapsList(List<Listings> body) {


        location.clear();


        for (int i = 0; i < body.size(); i++) {
            if (body.get(i).getLatitude() != 0.0d) {
                location.add(body.get(i));
            }
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



}

