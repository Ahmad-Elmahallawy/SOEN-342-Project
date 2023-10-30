
public class Driver {

	public static void main(String[] args) {
		
		SensorRegistry sreg = new SensorRegistry();
		LocationRegistry lreg = new LocationRegistry();
		MapTable mapt = new MapTable();
		ReadTable readt = new ReadTable();
		
		Console c = new Console(sreg, lreg, mapt, readt);
		
		Sensor s1 = new Sensor();
		Sensor s2 = new Sensor();
		Sensor s3 = new Sensor();
		Sensor s4 = new Sensor();
		
		Location l1 = new Location(45.5, -75.6);
		Location l2 = new Location(53.7798, 7.3055);
		
		Temperature t1 = new Temperature(18);
		Temperature t2 = new Temperature(21);

		sreg.addSensor(s1);
		sreg.addSensor(s2);
		sreg.addSensor(s3);
		sreg.addSensor(s4);
		
		c.deploySensor(s1, l1, t1);

		System.out.println(c.readTemperature(l1).getTemperature());
		c.readTemperature(l2);
	}

}
