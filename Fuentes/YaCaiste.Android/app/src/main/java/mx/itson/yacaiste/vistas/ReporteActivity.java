package mx.itson.yacaiste.vistas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mx.itson.yacaiste.R;
import mx.itson.yacaiste.entidades.ReporteEntity;

public class ReporteActivity extends AppCompatActivity {
    MapView mapView;
    GoogleMap map;
    ReporteEntity reporteEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        final String mCollapsedTitle = getString(R.string.title_activity_reporte);
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0 || verticalOffset <= toolbar.getHeight() && !toolbar.getTitle().equals(mCollapsedTitle)) {
                    collapsingToolbarLayout.setTitle("");
                } else if (!toolbar.getTitle().equals("")) {
                    collapsingToolbarLayout.setTitle(mCollapsedTitle);
                }
            }
        });
        setSupportActionBar(toolbar);
        reporteEntity = new ReporteEntity();
        reporteEntity.setId(getIntent().getIntExtra("ID_REPORTE", 0));
        final double LatLng[] = {27.944666, -110.927136};
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = String.format("google.streetview:cbll=%s,%s", LatLng[0], LatLng[1]);
                Uri gmmIntentUri = Uri.parse(location);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(false);
        map.getUiSettings().setAllGesturesEnabled(false);
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(LatLng[0], LatLng[1])).title("My Mark");
        map.addMarker(markerOptions);
        map.getUiSettings().setMapToolbarEnabled(false);
        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this);

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(LatLng[0], LatLng[1]), 18);
        map.animateCamera(cameraUpdate);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
