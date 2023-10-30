
public class Sensor {
	private boolean isDeployed;
	
	public Sensor() {
		isDeployed = false;
	}
	
	public Sensor(boolean b) {
		isDeployed = b;
	}
		
	public void setIsDeployed(boolean b) {
		isDeployed = b;
	}
	
	public boolean getIsDeployed() {
		return isDeployed;
	}
		
}
