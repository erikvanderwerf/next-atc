package tools;

public class Vector3 {
	public int[] dimensions;
	
	public Vector3() {
		dimensions = new int[]{0,0,0};
	}
	
	public Vector3(int x, int y, int z) {
		dimensions = new int[3];
		dimensions[0] = x;
		dimensions[1] = y;
		dimensions[2] = z;
	}
	
	public void delta(Vector3 other) {
		dimensions[0] += other.dimensions[0];
		dimensions[1] += other.dimensions[1];
		dimensions[2] += other.dimensions[2];
	}
	
	public Vector3 multiply(float scalar) {
		return new Vector3(
				(int) (dimensions[0] * scalar),
				(int) (dimensions[1] * scalar),
				(int) (dimensions[2] * scalar));
	}
}
