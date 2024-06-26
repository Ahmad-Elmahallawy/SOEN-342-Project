import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver {

	public static void main(String[] args) {
		SensorRegistry sreg = new SensorRegistry(new DeployedRegistry());
		LocationRegistry lreg = new LocationRegistry();
		MapTable mapt = new MapTable();
		ReadTable readt = new ReadTable();
		Console console = new Console(sreg, lreg, mapt, readt);

		// Sensor registry
		String sensorsPath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\input_files\\sensors.txt";
		// Map Table
		String mapTablePath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\output_files\\mapTable.txt";
		// Read Table
		String readTablePath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\output_files\\readTable.txt";
		// Location Registry
		String locationsPath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\output_files\\locations.txt";
		// Deployed Registry
		String deployedPath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\output_files\\deployed.txt";
		// Output file for identifying Sensors in the system and their type
		String sensorsClassPath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\output_files\\sensorsClass.txt";

		// These act as queries
		String deploySensorPath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\input_files\\deploySensor.txt";
		String readTemperaturePath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\input_files\\readTemperature.txt";
		String undeploySensorPath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\input_files\\undeploySensor.txt";
		String moveToNewLocationPath = "C:\\Users\\Vanessa\\SOEN342\\SOEN-342-Project\\Iteration2\\implementation\\input_files\\moveToNewLocation.txt";
	
	
		// -------------- Part 1: deploy sensors and read temperautres ---------
		// We require the sensors in the system
		readFromFile(console, "sensorsPath", sensorsPath); 
	
		// Let's deploy some sensors
		readFromFile(console, "deploySensorPath", deploySensorPath);
		// Let's read some temperatures
		readFromFile(console, "readTemperaturePath", readTemperaturePath);

		// -------------- Part 2: undeploy sensors and move some sensors to a new location ---------
		// Let's undeploy some sensors
		readFromFile(console, "undeploySensorPath", undeploySensorPath);
		// Let's move some sensors to new locations
		readFromFile(console, "moveToNewLocationPath", moveToNewLocationPath);

		// Let's write a file for all the deployed sensors
		writeToFile(console, "deployedPath", deployedPath);
		// Let's write a file for all the locations 
		writeToFile(console, "locationsPath", locationsPath);
		// Let's write a file for all the deployed sensor and location pairs
		writeToFile(console, "mapTablePath", mapTablePath);
		// Let's write a file for all the deployed sensor and location pairs
		writeToFile(console, "readTablePath", readTablePath);

		// This to verify that all the sensors have been initialized into objects 
		// (and verify that the object types reflect if they are sensors or deployed sensors)
		writeToFile(console, "sensorsClassPath", sensorsClassPath);
	}

   	private static void printAllLocations(PrintWriter pw, ArrayList<Location> locations) {
		if (locations.size() == 0)
				pw.println("No Locations in Location Registry");
		else {
			for (Location location : locations) {
				double latitude = location.getLatitude();
				double longitude = location.getLongitude();
				pw.println(longitude + ", " + latitude);
			}
		}
	}

	private static void printAllDeployed(PrintWriter pw, ArrayList<DeployedSensor> deployed) {
		if (deployed.size() == 0)
			pw.println("No sensors have been deployed");
		else {
			for (DeployedSensor sensor : deployed) {
				pw.println(sensor.toString());
			}
		}
	}


	private static void printAllSensors(PrintWriter pw, ArrayList<Sensor> sensors) {
		if (sensors.size() == 0)
			pw.println("No sensors in sensor registry");
		else {
			for (Sensor sensor : sensors) {
				pw.println(sensor.toString() + " : " + sensor.getClass());
			}
		}
	}
	
	private static void printAllMaps(PrintWriter pw, HashMap<DeployedSensor, Location> maps) {
		if (maps.size() == 0)
			pw.println("No DeployedSensor-Location pairs in Map Table");
		else {
			for (Map.Entry<DeployedSensor, Location> entry : maps.entrySet()) {
				DeployedSensor sensor = entry.getKey();
				Location location = entry.getValue();

				pw.println(sensor.toString() + ", " + location.toString());
			}
		}	
	}

	private static void printAllReads(PrintWriter pw, HashMap<DeployedSensor, Temperature> reads) {
	
		if (reads.size() == 0)
			pw.println("No DeployedSensor-Location pairs in Map Table");
		else {
			for (Map.Entry<DeployedSensor, Temperature> entry : reads.entrySet()) {
				DeployedSensor sensor = entry.getKey();
				Temperature temperature = entry.getValue();

				pw.println(sensor.toString() + ", " + temperature.toString());
			}
		}
	}

	// Method to write to the file
	private static void writeToFile(Console c, String name, String filePath) {
		try {
			// Create a FileOutputStream to write to the file
			FileOutputStream fos = new FileOutputStream(filePath);

			// Create a PrintWriter to write formatted text to the file
			PrintWriter pw = new PrintWriter(fos);

		if (name.equals("locationsPath")) {
			printAllLocations(pw, c.getAllLocations());   
			} 
			else if (name.equals("deployedPath")) {
				printAllDeployed(pw, c.getAllDeployedSensors());   
			}
			else if (name.equals("mapTablePath")) {
				printAllMaps(pw, c.getAllMaps());   
			}
			else if (name.equals("readTablePath")) {
				printAllReads(pw, c.getAllReads());   
			}
			else if (name.equals("sensorsClassPath")) {
				printAllSensors(pw, c.getAllSensors());   
			}
			// Close the PrintWriter and FileOutputStream
			pw.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to read from the file
   	private static void readFromFile(Console c, String name, String filePath) {
		try {
			// Create a FileReader to read from the file
			FileReader fileReader = new FileReader(filePath);

			// Create a BufferedReader to read lines efficiently
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String[] fileArr = filePath.split("\\\\");
			String fileName = fileArr[fileArr.length-1];
			System.out.println("##################### Read from file: " + fileName + " #####################");
			String line;
			if (name.equals("sensorsPath")) {
				while ((line = bufferedReader.readLine()) != null) {
					c.addSensor(Integer.parseInt(line));
					// Process each line as needed
					System.out.println("Read from sid: " + line);
				}   
			} 
			else if (name.equals("locationsPath")) {
				while ((line = bufferedReader.readLine()) != null) {
					String[] loc = line.split(",");
					System.out.println("Read from longitude: " + loc[0] + " and latitude: " + loc[1]);
					c.addLocation(Double.parseDouble(loc[0]), Double.parseDouble(loc[1]));
				}
			}
			else if (name.equals("deploySensorPath")) {
				while ((line = bufferedReader.readLine()) != null) {
					// Process each line as needed
					System.out.println("Read from line: " + line);
					String[] arr = line.split(",");

					int sid = Integer.parseInt(arr[0]);
					double longitude = Double.parseDouble(arr[1]);
					double latitude = Double.parseDouble(arr[2]);
					double tval = Double.parseDouble(arr[3]);

					// Find if sensor is already deployed
					DeployedSensor depl = c.getDeployedSensor(sid);
					// find if location is already covered
					Location loc = c.getLocation(longitude, latitude);
					if (loc != null)
						Message.locationAlreadyCovered();
					if (depl != null)
						Message.sensorAlreadyDeployed();
					if (loc == null && depl == null) {
						// Find sensor in sensorRegistry
						Sensor sensor = c.getSensor(sid);
						// Create location 
						Location location = c.addLocation(longitude, latitude);
						c.deploySensor(sensor, location, new Temperature(tval));
					}
					// Success message
					Message.successMessage();
					} 
			}
			else if (name.equals("readTemperaturePath")) {
				while ((line = bufferedReader.readLine()) != null) {
					// Process each line as needed
					System.out.println("Read from line: " + line);
					String[] loc = line.split(",");

					double longitude = Double.parseDouble(loc[0]);
					double latitude = Double.parseDouble(loc[1]);

					// find existing location in registry
					Location location = c.getLocation(longitude, latitude);

					if (location != null) {
						Temperature t = c.readTemperature(location);
						System.out.println(t.toString());
						// Success message
						Message.successMessage();
					}
					else
						// Error message
						Message.locationNotCovered();  
				}
			}
			else if (name.equals("undeploySensorPath")) {
				while ((line = bufferedReader.readLine()) != null) {
					// Process each line as needed
					System.out.println("Read from line: " + line);
					int sid = Integer.parseInt(line);

					// Find if sensor is deployed
					DeployedSensor deployed = c.getDeployedSensor(sid);
					if (deployed == null)
						Message.sensorNotDeployed();
					else {
						c.undeploySensor(deployed);
						Message.successMessage();
					}
				}
			}
			else if (name.equals("moveToNewLocationPath")) {
				while ((line = bufferedReader.readLine()) != null) {
					// Process each line as needed
					System.out.println("Read from line: " + line);
					String[] arr = line.split(",");

					int sid = Integer.parseInt(arr[0]);
					double longitude = Double.parseDouble(arr[1]);
					double latitude = Double.parseDouble(arr[2]);

					// check sensor is already deployed
					DeployedSensor deployed = c.getDeployedSensor(sid);
					// check new location is already covered
					Location loc = c.getLocation(longitude, latitude);
			
					if (loc != null)
						Message.locationAlreadyCovered();
					if (deployed == null)
						Message.sensorNotDeployed();
					if (loc == null && deployed != null) {
						// Create new location 
						Location location = c.addLocation(longitude, latitude);
						c.moveToNewLocation(deployed, location);
						// Success message
						Message.successMessage();
					}
				}
			}

			// Close the BufferedReader and FileReader
			bufferedReader.close();
			fileReader.close();
	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
        

    


