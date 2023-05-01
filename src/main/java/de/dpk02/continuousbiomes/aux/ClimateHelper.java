package de.dpk02.continuousbiomes.aux;

public class ClimateHelper {
	public static float tempMod(float value, int axis) {
		float co = ((float) axis)/2000f;
		return MathHelper.clamp(
			value * 
			MathHelper.abs(
				MathHelper.clamp(
					co,
					-1.0f,
					1.0f)
			) + 
			co,
			-2.0f,
			2.0f
		);
	}
	
	public static float humMod(float value, int primeAxis, int secondaryAxis) {
		float co1 = ((float) primeAxis)/1000f;
		float co2 = ((float) secondaryAxis)/4000f;
		return MathHelper.clamp(
			value * 
			MathHelper.abs(
				MathHelper.clamp(
					co1,
					-1.0f,
					1.0f)
			)
			- MathHelper.abs(co2)
			+ 0.25f,
			-2.0f,
			2.0f
		);
	}
}
