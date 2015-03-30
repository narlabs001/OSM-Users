package com.example.osm;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import android.app.Activity;
import android.os.Bundle;



public class Page_map extends Activity {
	
	private MapView mapview;
	private MapController mcontrol;
	private ScaleBarOverlay scale;
	private MyLocationNewOverlay mMyLocationOverlay;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_start);
		initialmap();
		mMyLocationOverlay = new MyLocationNewOverlay(this, mapview);
		mMyLocationOverlay.disableMyLocation();
		mMyLocationOverlay.disableFollowLocation();
		mMyLocationOverlay.setDrawAccuracyEnabled(true);
		mMyLocationOverlay.runOnFirstFix(new Runnable(){
			public void run(){
				mcontrol.animateTo(mMyLocationOverlay.getMyLocation());
			}
		});
		mapview.getOverlays().add(mMyLocationOverlay);
	}
	public void initialmap(){
		mapview = (MapView) this.findViewById(R.id.mapview);
		mapview.setTileSource(TileSourceFactory.MAPQUESTOSM);
		mapview.setBuiltInZoomControls(true);
		mapview.setMultiTouchControls(true);
		mcontrol = (MapController) this.mapview.getController();
		mcontrol.setZoom(5);
		}
}
