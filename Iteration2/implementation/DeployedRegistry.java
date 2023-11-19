import java.util.ArrayList;

public class DeployedRegistry {
    	private ArrayList<DeployedSensor> deployed = new ArrayList<DeployedSensor> ();

    	// add sensor to deployed given the sensor
    	public DeployedSensor addDeployed(Sensor sensor) {
        	if (checkUniqueID(sensor.getID())) {
            		DeployedSensor depl = new DeployedSensor(sensor.getID());
		    	deployed.add(depl);
            		return depl;
        	}
        	else {
            		Message.nonUniqueSensorID();
            		return null;
        	}
	}

    	// remove sensor from deployed
	public void removeDeployed(DeployedSensor sensor) {
		deployed.remove(sensor);
	}

    	// finds sensor in deployed from sid, returns sensor 
    	public DeployedSensor findDeployed(int sid) {
        	for (DeployedSensor sensor : deployed) {
            		if (sensor.getID() == sid) {
                	return sensor;
            		}
        	}
        	return null;
    	}
	
    	// internal method
    	// check unique id of sensor
    	private boolean checkUniqueID(int sid) {
		for (DeployedSensor sensor : deployed) {
            		if (sensor.getID() == sid) {
                		return false;
            		}
        	}
        	return true;
	}

	// ----- get objects to write to the text file -----
    	public ArrayList<DeployedSensor> getAllDeployed() {
		return deployed;
	}
	// ------------------------------------------------
}
    
