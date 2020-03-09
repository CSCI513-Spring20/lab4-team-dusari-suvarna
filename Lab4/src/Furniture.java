import java.io.Serializable;

public class Furniture implements Serializable, HouseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String blockName;
	public Furniture(String blockName){
		this.blockName = blockName;
	}
	
 	@Override
	public void listHouseSpecs(int level) {
		StringBuffer sb = new StringBuffer();
		for(int j = 0; j < level; j++)
			sb.append("   ");			
		System.out.println(sb.toString() + blockName);		
	}

	@Override
	public int countContents() {
		return 1;
	}    
}