import java.util.HashMap;
import java.util.Map.Entry;

public class MapTable {

	private HashMap<DeployedSensor, Location> maps = new HashMap<DeployedSensor, Location> ();
	
	// creates map
	public void makeNewMap(DeployedSensor sensor, Location location) {
		maps.put(sensor, location);
	}

	// removes map
	public void removeMap(DeployedSensor sensor) {
		maps.remove(sensor);
	}

	// find map with mapping (sensor, location) given the location, returns the sensor
	public DeployedSensor findSensor(Location location) {
		for (Entry<DeployedSensor, Location> entry: maps.entrySet()) {
			if (entry.getValue().equals(location)) {
				DeployedSensor s = entry.getKey();
				return s;
			}
		}
		return null;
	}

	public Location findLocation(DeployedSensor sensor) {
		return maps.get(sensor);
	}

}
