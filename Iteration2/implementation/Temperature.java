public class Temperature {
	private double tempValue;
	
	public Temperature(double t) {
		tempValue = t;
	}
	
	public void setTemperature(double t) {
		tempValue = t;
	}
	
	public double getTemperature() {
		return tempValue;
	}

	public String toString() {
		return String.valueOf(tempValue);
	}
	
}
