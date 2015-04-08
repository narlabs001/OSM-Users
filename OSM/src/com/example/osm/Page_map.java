package com.example.osm;

import java.util.ArrayList;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.SimpleLocationOverlay;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;




public class Page_map extends Activity {
	
	private MapView mapview;
	private MapController mcontrol;
	private ScaleBarOverlay scale;
	public LocationManager locationManager = null;
	Location location;
	//private MyLocationNewOverlay mMyLocationOverlay;
	ArrayList<OverlayItem> overlayArray;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_start);
		initialmap();
		
		location = getMyLocation();
		if(location == null){
			Toast.makeText(getApplicationContext(), "No Location Found", Toast.LENGTH_SHORT).show();
		}
		else{
		GeoPoint myLocation = new GeoPoint(location);
		MyLocationNewOverlay myLocationOverlay = new MyLocationNewOverlay(this, mapview);
		mcontrol.setCenter(myLocation);
		
		mapview.getOverlays().add(myLocationOverlay);
		}
		
		
		/*
		mMyLocationOverlay = new MyLocationNewOverlay(this.getBaseContext(),mapview);
		mMyLocationOverlay.enableMyLocation();
		mMyLocationOverlay.enableFollowLocation();
		mMyLocationOverlay.setDrawAccuracyEnabled(true);
		mMyLocationOverlay.runOnFirstFix(new Runnable(){
			public void run(){
				mcontrol.animateTo(mMyLocationOverlay.getMyLocation());
				Toast.makeText(getApplicationContext(), "work", Toast.LENGTH_LONG).show();
			}
		})*/;
		
	}
	public void initialmap(){
		mapview = (MapView) this.findViewById(R.id.mapview);
		mapview.setTileSource(TileSourceFactory.MAPQUESTOSM);
		mapview.setBuiltInZoomControls(true);
		mapview.setMultiTouchControls(true);
		mcontrol = (MapController) this.mapview.getController();
		mcontrol.setZoom(15);
		}
	
	Location getMyLocation(){
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		 
		//return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		return locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		
	}
}
