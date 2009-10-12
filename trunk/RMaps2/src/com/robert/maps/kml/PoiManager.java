package com.robert.maps.kml;

import java.util.ArrayList;
import java.util.List;

import org.andnav.osm.util.GeoPoint;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.robert.maps.PoiActivity;

public class PoiManager {
	protected final Context mCtx;
	private GeoDatabase mGeoDatabase;

	public PoiManager(Context ctx) {
		super();
		mCtx = ctx;
		mGeoDatabase = new GeoDatabase(ctx);
	}


	public void addPoi(GeoPoint point){
		mGeoDatabase.addPoi("testpoi", "Test POI 1", point.getLatitude(), point.getLongitude(), 0, 0, 0);
	}

	public void addPoi(final String title, final String descr, GeoPoint point){
		mGeoDatabase.addPoi(title, descr, point.getLatitude(), point.getLongitude(), 0, 0, 0);
	}

	public List<PoiPoint> getPoiList() {
		final ArrayList<PoiPoint> items = new ArrayList<PoiPoint>();
		final Cursor c = mGeoDatabase.getPoiListCursor();
		if (c != null) {
			if (c.moveToFirst()) {
				do {
					items.add(new PoiPoint(c.getString(2), c.getString(3), new GeoPoint(
							(int) (1E6 * c.getDouble(0)), (int) (1E6 * c.getDouble(1)))));
				} while (c.moveToNext());
			}
			c.close();
		}

		return items;
	}


	public void addPoiStartActivity(Context ctx, GeoPoint touchDownPoint) {
		ctx.startActivity((new Intent(ctx, PoiActivity.class)).putExtra("lat",
				touchDownPoint.getLatitude()).putExtra("lon",
				touchDownPoint.getLongitude()));
	}
}