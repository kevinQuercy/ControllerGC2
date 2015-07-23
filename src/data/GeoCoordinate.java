package data;

/** @file
 * 
 * Manage geographic coordinates, compute distance
 *
 * Reference: http://www.movable-type.co.uk/scripts/latlong.html, Spherical Law of Cosines
 */

public class GeoCoordinate {
	private double latitude;  // floating value in degrees, + for North, - for South
	private double longitude; // floating value in degrees, + for East, - for West

	public GeoCoordinate(double latitute, double longitude) {
		super();
		this.latitude = latitute;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitute) {
		this.latitude = latitute;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	// get distance to another point in metres
	public double distanceTo(GeoCoordinate geo) {
		return distance(this, geo);
	}
	
	// get distance between 2 points in metres
	private static final double earthRadius = 6371000; // metres 
	static public double distance(GeoCoordinate geo1, GeoCoordinate geo2) {
		double lat1Rad = Math.toRadians(geo1.latitude);
		double lat2Rad = Math.toRadians(geo2.latitude);
		double deltaLongRad = Math.toRadians(geo2.longitude-geo1.longitude);
		
		return Math.acos(Math.sin(lat1Rad)*Math.sin(lat2Rad)+Math.cos(lat1Rad)*Math.cos(lat2Rad)*Math.cos(deltaLongRad))*earthRadius;
	}
}
