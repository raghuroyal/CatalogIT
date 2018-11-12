package generalUtilities;

public class Halt {
	
	
	public void staticWait(int timeInSec) {
		
		try {
			Thread.sleep(timeInSec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
