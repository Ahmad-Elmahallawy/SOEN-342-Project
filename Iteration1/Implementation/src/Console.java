
public class Console {
	
	private SensorRegistry sensorRegistry;
	private LocationRegistry locationRegistry;
	private MapTable mapTable;
	private ReadTable readTable;

	public Console(SensorRegistry sensorRegistry, LocationRegistry locationRegistry, MapTable mapTable, ReadTable readTable) {
		this.sensorRegistry = sensorRegistry;
		this.locationRegistry = locationRegistry;
		this.mapTable = mapTable;
		this.readTable = readTable;
	}

	public boolean deploySensor(Sensor sensor, Location location, Temperature temperature) {
		// Find if sensor is already deployed
		Sensor s = sensorRegistry.findSensor(sensor);
		boolean depl = s.getIsDeployed(); 
		// find existing location in registry
		boolean lac = locationRegistry.findCurrentLocation(location);
		
		if (lac)
			Message.locationAlreadyCovered();
		if (depl)
			Message.sensorDeployed();

		if (!(lac || depl)) {
			// Create new map
			mapTable.makeNewMap(sensor, location);
			// Create new read
			readTable.makeNewRead(sensor, temperature);

			// Sensor is now deployed
			sensor.setIsDeployed(true); 
			// Add location to Location Registry
			locationRegistry.addNewLocation(location);

			// Success message
			Message.successMessage();
			return true;
		}
		else
			return false;
	}
	
	public Temperature readTemperature(Location location) {
		// find existing location in registry
		boolean lac = locationRegistry.findCurrentLocation(location);
		
		if (lac) {
			Sensor sensor = mapTable.findSensor(location);
			Temperature temperature = readTable.findTemperature(sensor);

			// Success message
			Message.successMessage();

			return temperature;
		}
		else
			Message.locationNotCovered();
			return null;
		
	}
}