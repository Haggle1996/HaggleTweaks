package haggle1996.haggletweaks.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import cpw.mods.fml.common.registry.GameRegistry;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import haggle1996.haggletweaks.Main;

public class FurnaceHelper {
    
    public static void init()
    {
        
		for (Iterator<Entry<ItemStack,ItemStack>> iter = FurnaceRecipes.smelting().getSmeltingList().entrySet().iterator(); iter.hasNext();)
        {
			Entry<ItemStack,ItemStack> entry = iter.next();
            
			try{
                Main.log.info("HaggleTweaks found valid furnace recipe: " + entry.getValue().toString() + " " + entry.getKey().toString());
			}catch(Throwable t){
				Main.log.info("HaggleTweaks is removing invalid furnace recipe.");
				iter.remove();
			}
		}
    }
    
}


