package mainengine;

public class MainEngineFactory {
	
	@SuppressWarnings("rawtypes")
	public IMainEngine createMainEngine(String engineType) {
		if(engineType.equals("MainEngine"))

			return new MainEngine(); 
		else
			System.out.println("Invalid type of MainEngine");
			return null;
	}
	
}//end class
