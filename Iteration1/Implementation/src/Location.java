public class Location {
	private double longitude;
	private double latitude;

	public Location() {
		longitude = 47;
		latitude = 100;
	}
	
	public Location(double lon, double lat) {
		longitude = lon;
		latitude = lat;
	}
	
	public void setLongitude(double l) {
		longitude = l;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLatitude(double l) {
		latitude = l;
	}
	
	public double getLatitude() {
		return latitude;
	}
}
	
