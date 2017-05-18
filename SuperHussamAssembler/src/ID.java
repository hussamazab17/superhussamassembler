
import java.awt.Color;

public enum ID {
	
	A, B, C;
	
	Class cl;
	
	ID(Class cl) {
		this.cl = cl;
	}
	
	Object getNewObject() throws Exception {
		switch(this) {
			case A:
				
				break;
			case B:
				
				break;
			case C:
				
				break;
		}
		
		return null;
	}
	
}
