import java.util.ArrayList;

public class SensorRegistry {
	private ArrayList<Sensor> sensors = new ArrayList<Sensor> ();
    private DeployedRegistry deployedRegistry;

	public SensorRegistry(DeployedRegistry dreg) {
		deployedRegistry = dreg;
	}

	// finds sensor in sensorRegistry from sid, returns sensor 
    public Sensor findSensor(int sid) {
        for (Sensor sensor : sensors) {
            if (sensor.getID() == sid) {
                return sensor;
            }
        }
        return null;
    }
}
