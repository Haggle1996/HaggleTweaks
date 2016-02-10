package haggle1996.haggletweaks;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import haggle1996.haggletweaks.helpers.OreDictionaryHelper;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.ItemStackHolderInjector;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;

@Mod(modid = Main.MODID, version = Main.VERSION, name = Main.NAME,  dependencies = "required-after:Forge@[10.13.3.1384,11.14);after:*;")
public class Main {
    public static final String MODID = "HaggleTweaks";
    public static final String NAME = "HaggleTweaks";
    public static final String VERSION = "0.1.0.0";
    public static final Logger log = LogManager.getLogger(MODID);
 
 	@Mod.EventHandler
	public void init (FMLInitializationEvent event)
	{
		ItemStackHolderInjector.INSTANCE.inject();
		Main.log.info("HaggleTweaking Started...");
		OreDictionaryHelper.init();
        Main.log.info("Finished HaggleTweaking");
	}
    
}