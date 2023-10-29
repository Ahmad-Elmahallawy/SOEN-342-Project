
public class Sensor {
	private boolean isDeployed;
	
	private Sensor() {
		isDeployed = false;
	}
	
	private Sensor(boolean b) {
		isDeployed = b;
	}
		
	public void setIsDeployed(boolean b) {
		isDeployed = b;
	}
	
	public boolean getIsDeployed() {
		return isDeployed;
	}
		
}
