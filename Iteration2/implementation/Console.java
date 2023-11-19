import java.util.ArrayList;
import java.util.HashMap;

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

	public Temperature readTemperature(Location location) {
		DeployedSensor sensor = mapTable.findSensor(location);
		Temperature temperature = readTable.findTemperature(sensor);
		return temperature;
	}

} 
