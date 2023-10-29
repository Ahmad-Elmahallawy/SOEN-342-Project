
public class Map {
	Sensor sensor;
	Location location;
	
	public Map(Sensor s, Location l) {
		sensor = s;
		location = l;
	}
	
	public void setSensor(Sensor s) {
		sensor = s;
	}
	
	public Sensor getSensor() {
		return sensor;
	}
	
	public void setLocation(Location l) {
		location = l;
	}
	
	public Location getLocation() {
		return location;
	}

}
