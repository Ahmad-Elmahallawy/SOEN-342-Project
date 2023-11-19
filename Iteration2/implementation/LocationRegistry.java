import java.util.ArrayList;

public class LocationRegistry {

	private ArrayList<Location> locations = new ArrayList<Location> ();
	
	// find location in registry, return location
	public Location findLocation(double longitude, double latitude) {
		for (Location loc : locations) {
            if ((loc.getLongitude() == longitude) && (loc.getLatitude() == latitude)) {
                return loc;
            }
        }
        return null;
	}
	// add location in registry
	public Location addLocation(double longitude, double latitude) {
		Location loc = new Location(longitude, latitude);
		locations.add(loc);
		return loc;
	}

	// remove sensor from sensors
	public void removeLocation(Location location) {
		locations.remove(location);
	}

	// return all location of interest
	public ArrayList<Location> getAllLocations() {
		return locations;
	}
}
