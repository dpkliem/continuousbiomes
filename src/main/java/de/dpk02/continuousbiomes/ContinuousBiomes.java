package de.dpk02.continuousbiomes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ContinuousBiomes.MODID)
public class ContinuousBiomes
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "continuousbiomes";

    public ContinuousBiomes()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
