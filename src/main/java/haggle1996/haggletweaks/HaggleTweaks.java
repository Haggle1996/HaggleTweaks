package haggle1996.haggletweaks;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumStatus;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import haggle1996.haggletweaks.integration.Bloodmoon;

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
    public static final String VERSION = "0.4.0.0";
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
    public void onCheckSpawn(LivingSpawnEvent.CheckSpawn event)
    {
        // spawning rules:
        // allow spanws anywhere during blood moon and invasion
        // otherwise
        // mobs can spawn at light level 0, underground
        if (event.entity instanceof IMob)
        {
            if (event.world.provider.dimensionId == 0)
            {
                // if bloodmoon is active, don't deny things
                if (!Bloodmoon.isBloodmoonActive())
                {
                    int light = event.world.getSavedLightValue(EnumSkyBlock.Sky, MathHelper.floor_float(event.x), MathHelper.floor_float(event.y), MathHelper.floor_float(event.z));
                    if (light > 0)
                    {
                        event.setResult(Result.DENY);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event)
    {
        Entity entity = event.entity;
        World world = entity.worldObj;
        
        // burn them all if light > 3!
        if (entity instanceof IMob && world.provider.dimensionId == 0 && !entity.isBurning() && !entity.isWet())
        {
            int x = (int) Math.floor(entity.posX);
            int y = (int) Math.floor(entity.posY);
            int z = (int) Math.floor(entity.posZ);

            int light = world.getSavedLightValue(EnumSkyBlock.Block, x, y, z);
            if (light > 7)
            {
                entity.setFire(8);
            }
        }
    }

}