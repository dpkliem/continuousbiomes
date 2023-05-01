package de.dpk02.continuousbiomes.aux;

public class MathHelper {
	
	public static Float clamp(float in, float min, float max) {
		return (in < min) ? min : ((in > max) ? max : in);
	}
	
	public static Float abs(float in) {
		return (in < 0) ? -1f * in : in;
	}
}
