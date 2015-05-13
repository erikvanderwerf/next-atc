package aircraft;

import tools.Vector3;

public class Aircraft {
	byte throttle;
	? rotation;

	Type type;

	Vector3 position;
	Vector3 velocity;
	
	public Aircraft(Type type) {
		this.type = type;
		
		position = new Vector3();
		velocity = new Vector3();
		
		throttle = 0;
	}
}
