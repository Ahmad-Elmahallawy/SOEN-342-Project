
public class Read {
	Sensor sensor;
	Temperature temperature;
	
	public Read(Sensor s, Temperature t) {
		sensor = s;
		temperature = t;
	}
	
	public void setSensor(Sensor s) {
		sensor = s;
	}
	
	public Sensor getSensor() {
		return sensor;
	}
	
	public void setTemperature(Temperature t) {
		temperature = t;
	}
	
	public Temperature getTemperature() {
		return temperature;
	}

}
