package haggle1996.haggletweaks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumStatus;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.ItemStackHolderInjector;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = HaggleTweaks.MODID, version = HaggleTweaks.VERSION, name = HaggleTweaks.NAME,  dependencies = "required-after:Forge@[10.13.3.1384,11.14);after:*;")
public class HaggleTweaks {
    public static final String MODID = "HaggleTweaks";
    public static final String NAME = "HaggleTweaks";
    public static final String VERSION = "0.3.0.0";
    public static final Logger log = LogManager.getLogger(MODID);
    
 	@Mod.EventHandler
	public void preInit (FMLPreInitializationEvent event)
	{
		
        HaggleTweaks.log.info("HaggleTweaking Started...");

        MinecraftForge.EVENT_BUS.register(this);

        HaggleTweaks.log.info("Finished HaggleTweaking");
	}

    @SubscribeEvent
    public void onPlayerSleepInBed(PlayerSleepInBedEvent event)
    {
        event.result = EnumStatus.OTHER_PROBLEM;

        if(event.entityPlayer.worldObj.provider.canRespawnHere())
        {
            event.entityPlayer.setSpawnChunk(new ChunkCoordinates(event.x,event.y,event.z), false);
            event.entityPlayer.addChatComponentMessage(new ChatComponentTranslation("haggletweaks.spawn.set"));
        }

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void CheckSpawn(LivingSpawnEvent.CheckSpawn event)
    {
        if (event.entity instanceof IMob)
        {
            if (event.world.provider.dimensionId == 0)
            {
                if (!event.world.isRaining())
                {
                    event.setResult(Result.DENY);
                }
            }
        }
    }

}