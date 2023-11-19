
public class Sensor {
	private int id;
	
	public Sensor(int sid) {
		id = sid; 
	}

	public void setID(int sid) {
		id = sid;
	}

	public int getID() {
		return id;
	}

	public String toString() {
		return String.valueOf(id);
	}
		
}
