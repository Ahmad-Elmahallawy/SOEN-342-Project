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
        String sensorsPath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\sensors.txt";
        // Map Table
        String mapTablePath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\mapTable.txt";
        // Read Table
        String readTablePath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\readTable.txt";
        // Location Registry
        String locationsPath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\locations.txt";
        // Deployed Registry
        String deployedPath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\deployed.txt";

        // These act as queries
        String deploySensorPath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\deploySensor.txt";
        String readTemperaturePath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\readTemperature.txt";
        String undeploySensorPath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\undeploySensor.txt";
        String moveToNewLocationPath = "C:\\Users\\Vanessa\\SOEN342\\implementation-FINAL\\src\\files\\moveToNewLocation.txt";
        
        
        // -------------- Part 1: deploy sensors and read temperautres ---------
        // We require for part 1 the sensors in the system
        readFromFile(console, "sensorsPath", sensorsPath); 
        
        // Let's deploy some sensors
        readFromFile(console, "deploySensorPath", deploySensorPath);
        // Let's read some temperatures
        readFromFile(console, "readTemperaturePath", readTemperaturePath);
        

        // This to verify that all the sensors have been initialized into objects 
        // (and verify that the object types reflect if they are sensors or deployed sensors)
        printAllSensors(console.getAllSensors());
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


    private static void printAllSensors(ArrayList<Sensor> sensors) {
        
        if (sensors.size() == 0)
            System.out.println("No sensors in sensor registry");
        else {
            for (Sensor sensor : sensors) {
                System.out.println(sensor.toString() + " : " + sensor.getClass());
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

            // Insert code here for writing to the file

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

            // Close the BufferedReader and FileReader
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
        

    


