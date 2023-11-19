import java.util.HashMap;

public class ReadTable {

	private HashMap<DeployedSensor, Temperature> reads = new HashMap<DeployedSensor, Temperature> ();
	
	// creates Read
	public void makeNewRead(DeployedSensor sensor, Temperature temperature) {
		reads.put(sensor, temperature);
	}

	// removes Read
	public void removeRead(DeployedSensor sensor) {
		reads.remove(sensor);
	}
	
	// finds read with mapping of (sensor, temperature) given the sensor, returns the temperature
	public Temperature findTemperature(DeployedSensor sensor) {
		return reads.get(sensor);
	}

	// ----- get objects to write to the text file -----
	public HashMap<DeployedSensor, Temperature> getAllReads() {
		return reads;
	}
}
