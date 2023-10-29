import java.util.HashMap;

public class ReadTable {

	private HashMap<Sensor, Temperature> reads = new HashMap<Sensor, Temperature> ();
	
	public void makeNewRead(Sensor sensor, Temperature temperature) {
		reads.put(sensor, temperature);
	}
	
	public Temperature findTemperature(Sensor sensor) {
		return reads.get(sensor);
	}
}
