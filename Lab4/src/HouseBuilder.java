import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class HouseBuilder extends Application{
	
	HouseArea house;
	/**
	 * Manually construct a house
	 */
	public void buildHouse(){
		Furniture block1 = new Furniture("Sink");
        Furniture block2 = new Furniture("Counter");
        Furniture block3 = new Furniture("Bed");
        Furniture block4 = new Furniture("Dresser");
        Furniture block5 = new Furniture("Bathtub");

        //Initialize composite structures
        HouseArea structure = new HouseArea("Kitchen");
        HouseArea structure1 = new HouseArea("Bedroom");
        HouseArea structure2 = new HouseArea("Bathroom");
        HouseArea structure3 = new HouseArea("Downstairs");
        HouseArea structure4 = new HouseArea("Upstairs");
        house = new HouseArea("House");
        
        //Build the house
        house.add(structure3);
        house.add(structure4);
        
        structure4.add(structure1);
        structure4.add(structure2);
        
        structure3.add(structure);
        
        structure.add(block1);
        structure.add(block2);
        structure1.add(block3);
        structure1.add(block4);
        structure2.add(block5);     
	}
	
	/**
	 * Save using serialization
	 * @param fileName
	 */
	public void save(String fileName){
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream( new FileOutputStream(fileName));
			oos.writeObject(house);  //serializing employee
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	public void countHouseContents(){
		System.out.println("House includes: " + house.countContents() + " areas and/or furniture items.");
	}
	
	public void printHouseSpecs(){
		house.listHouseSpecs(0);
	}
	
	public HouseArea getHouse(){
		return house;
	}
	
	
	/**
	 * Restore from serialized form
	 * @param fileName
	 */
	public void restore(String fileName){
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream( new FileInputStream(fileName));
			house = (HouseArea) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public String getFileName(Stage primaryStage){
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setInitialDirectory(new File("/Users/vishwatejasuvarna/git/lab4-team-dusari-suvarna"));  // This is optional
		 fileChooser.setTitle("Serialization File");
		 File file = fileChooser.showOpenDialog(primaryStage);
		 return file.getAbsolutePath();
	}
	
	 public static void main(String[] args) {
		 launch(args);      
	 }

	@Override
	public void start(Stage primaryStage) throws Exception {
		  HouseBuilder houseBuilder = new HouseBuilder();
	      houseBuilder.buildHouse();
	      houseBuilder.save("/Users/vishwatejasuvarna/git/lab4-team-dusari-suvarna/Lab4/myHouse.ser");
	      String filename = houseBuilder.getFileName(primaryStage);
	      houseBuilder.restore(filename);
	      houseBuilder.printHouseSpecs();
	      houseBuilder.countHouseContents();		
	}      	       
}
