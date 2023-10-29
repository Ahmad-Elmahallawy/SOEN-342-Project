import java.util.HashMap;
import java.util.Map.Entry;

public class MapTable {

	private HashMap<Sensor, Location> maps = new HashMap<Sensor, Location> ();
	
	public void makeNewMap(Sensor sensor, Location location) {
		maps.put(sensor, location);
	}
	
	public Sensor findSensor(Location location) {
		for (Entry<Sensor, Location> entry: maps.entrySet()) {
			if (entry.getValue().equals(location)) {
				Sensor s = entry.getKey();
				return s;
			}
		}
		return null;
	}
}
