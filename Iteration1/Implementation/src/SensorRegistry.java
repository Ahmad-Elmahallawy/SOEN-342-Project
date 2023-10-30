import java.util.ArrayList;

public class SensorRegistry {
	private ArrayList<Sensor> sensors = new ArrayList<Sensor> ();
	
	public Sensor findSensor(Sensor sensor) {
		int index = sensors.indexOf(sensor);
		return sensors.get(index);
	}

	public void addSensor(Sensor sensor) {
		sensors.add(sensor);
	}

}
