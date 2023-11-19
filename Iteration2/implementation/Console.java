import java.util.ArrayList;
import java.util.HashMap;

public class Console {
	
	private SensorRegistry sensorRegistry;
	private LocationRegistry locationRegistry;
	private MapTable mapTable;
	private ReadTable readTable;

	// constructor
	public Console(SensorRegistry sensorRegistry, LocationRegistry locationRegistry, MapTable mapTable, ReadTable readTable) {
		this.sensorRegistry = sensorRegistry;
		this.locationRegistry = locationRegistry;
		this.mapTable = mapTable;
		this.readTable = readTable;
	}
	
	public Temperature readTemperature(Location location) {
		DeployedSensor sensor = mapTable.findSensor(location);
		Temperature temperature = readTable.findTemperature(sensor);
		return temperature;
	}

	public void deploySensor(Sensor sensor, Location location, Temperature temperature) {
		// Add sensor to deployRegistry
		DeployedSensor deployed = sensorRegistry.addDeployedSensor(sensor);
		// Create new map
		mapTable.makeNewMap(deployed, location);
		// Create new read
		readTable.makeNewRead(deployed, temperature);
	}

	public void undeploySensor(DeployedSensor sensor) {
		// Remove sensor from deployedRegistry
		sensorRegistry.removeDeployedSensor(sensor);
		// Remove map
		mapTable.removeMap(sensor);
		// Remove read
		readTable.removeRead(sensor);
	}

	public void moveToNewLocation(DeployedSensor sensor, Location location) {
		// move sensor to new location
		Location oldLocation = mapTable.findLocation(sensor);
		mapTable.removeMap(sensor);
		locationRegistry.removeLocation(oldLocation);
		mapTable.makeNewMap(sensor, location);
	}

	public ArrayList<Location> getAllLocations() {
		return locationRegistry.getAllLocations();
	}

	public Location getLocation(double longitude, double latitude) {
		return locationRegistry.findLocation(longitude, latitude);
	}

	public DeployedSensor getDeployedSensor(int sid) {
		return sensorRegistry.findDeployedSensor(sid);
	}

	public Sensor getSensor(int sid) {
		return sensorRegistry.findSensor(sid);
	}

	// ----- create objects from the text file ----
	public void addSensor(int sid) {
		sensorRegistry.addSensor(sid);
	}

	public Location addLocation(double longitude, double latitude) {
		return locationRegistry.addLocation(longitude, latitude);
	}
	// ------------------------------------------------

	// ----- get objects to write to the text file -----
	public ArrayList<DeployedSensor> getAllDeployedSensors() {
		return sensorRegistry.getAllDeployedSensors();
	}

	public ArrayList<Sensor> getAllSensors() {
		return sensorRegistry.getAllSensors();
	}

	public HashMap<DeployedSensor, Location> getAllMaps() {
		return mapTable.getAllMaps();
	}

	public HashMap<DeployedSensor, Temperature> getAllReads() {
		return readTable.getAllReads();
	}

} 
