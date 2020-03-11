public abstract class HouseFactory {
	public abstract HouseEntity createHouse(String item);
	
	public HouseEntity buildHouse(String type)
	{
		HouseEntity house = createHouse(type);
		return house;
	}
}