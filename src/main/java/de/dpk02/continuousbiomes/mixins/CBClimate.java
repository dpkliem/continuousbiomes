package de.dpk02.continuousbiomes.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Redirect;

import de.dpk02.continuousbiomes.aux.ClimateHelper;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.QuartPos;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.DensityFunction;

@Mixin(Climate.Sampler.class)
public abstract class CBClimate {
	
	@Shadow private DensityFunction temperature;
	@Shadow private DensityFunction humidity;
	@Shadow private DensityFunction continentalness;
	@Shadow private DensityFunction erosion;
	@Shadow private DensityFunction depth;
	@Shadow private DensityFunction weirdness;
	@Shadow private List<Climate.ParameterPoint> spawnTarget;
	
	@Shadow
	public abstract Climate.TargetPoint sample(int x, int y, int z);

	@Redirect(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Climate;target(FFFFFF)Lnet/minecraft/world/level/biome/Climate$TargetPoint;"))
	public Climate.TargetPoint onSample(float temperature, float humidity, float continentalness, float erosion, float depth, float weirdness, int x, int y, int z) {
		int i = QuartPos.toBlock(x);
		int j = QuartPos.toBlock(y);
		int k = QuartPos.toBlock(z);
		DensityFunction.SinglePointContext densityfunction$singlepointcontext = new DensityFunction.SinglePointContext(i, j, k);
		return Climate.target(
			ClimateHelper.tempMod((float)this.temperature.compute(densityfunction$singlepointcontext), z), 
			ClimateHelper.humMod((float)this.humidity.compute(densityfunction$singlepointcontext), -x, z), 
			(float)this.continentalness.compute(densityfunction$singlepointcontext), 
			(float)this.erosion.compute(densityfunction$singlepointcontext), 
			(float)this.depth.compute(densityfunction$singlepointcontext), 
			(float)this.weirdness.compute(densityfunction$singlepointcontext)
		);
	}
}
