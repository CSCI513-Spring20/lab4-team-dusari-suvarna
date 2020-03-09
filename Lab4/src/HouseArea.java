import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HouseArea implements Serializable, HouseEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// List of children
	private List<HouseEntity> childGroup = new ArrayList<HouseEntity>();
	String blockName;
	public HouseArea(String blockName){
		this.blockName = blockName;
	}
	
	public void add(HouseEntity group) {
		childGroup.add(group);
	}
	
	public void remove(HouseEntity group) {
		childGroup.remove(group);
	}
	
	@Override
	public void listHouseSpecs(int level) {

		// First display the current group
		StringBuffer sb = new StringBuffer();
		for(int j = 0; j < level; j++)
			sb.append("   ");
		System.out.println(sb.toString() + blockName);
		
		// Now delegate the task of display to any children
		for(HouseEntity group: childGroup){
			group.listHouseSpecs(level+1);
		}	
	}

	@Override
	public int countContents() {
		int contents = 0;
		for(HouseEntity child: childGroup){
			contents += child.countContents();
		}
		return contents +1;
	}
}