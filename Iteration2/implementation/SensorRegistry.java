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
	
	public DeployedSensor addDeployedSensor(Sensor sensor) {
        	DeployedSensor deployedSensor = deployedRegistry.addDeployed(sensor);
		// Replace sensor in sensorRegistry with deployedSensor
        	sensors.set(sensors.indexOf(sensor), deployedSensor);
		return deployedSensor;
	}

	public void removeDeployedSensor(DeployedSensor sensor) {
		deployedRegistry.removeDeployed(sensor);
		// Replace deployedSensor in sensorRegistry with sensor
		Sensor undeployedSensor = new Sensor(sensor.getID());
        	sensors.set(sensors.indexOf(sensor), undeployedSensor);
	}
	
    	public DeployedSensor findDeployedSensor(int sid) {
		return deployedRegistry.findDeployed(sid);
	}

	// ----- create objects from the text file -----
   	 public void addSensor(int sid) {
      		Sensor sensor = new Sensor(sid);
		sensors.add(sensor);
	}

	// ----- get objects to write to the text file ----
	public ArrayList<Sensor> getAllSensors() {
		return sensors;
	}

	public ArrayList<DeployedSensor> getAllDeployedSensors() {
		return deployedRegistry.getAllDeployed();
	}
	// ------------------------------------------------
}
