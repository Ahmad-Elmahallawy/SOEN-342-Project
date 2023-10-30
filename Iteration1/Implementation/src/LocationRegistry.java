import java.util.ArrayList;

public class LocationRegistry {
	private ArrayList<Location> locations = new ArrayList<Location> ();
	
	public boolean findCurrentLocation(Location location) {
		return locations.contains(location);
	}
	
	public void addNewLocation(Location location) {
		// Location l = new Location();
		locations.add(location);
	}
	
}
