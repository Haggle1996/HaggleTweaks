package haggle1996.haggletweaks.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry.ItemStackHolder;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import haggle1996.haggletweaks.Main;

public class OreDictionaryHelper {

    private static Field OREDICTIONARY_IDTOSTACK;

    static {
		try {
			OREDICTIONARY_IDTOSTACK = OreDictionary.class
					.getDeclaredField("idToStack");
			OREDICTIONARY_IDTOSTACK.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
    
	public static void remove(int id, ItemStack item) {
		try {
			@SuppressWarnings("unchecked")
			List<ArrayList<ItemStack>> idToStack = (List<ArrayList<ItemStack>>) OREDICTIONARY_IDTOSTACK.get(null);
			ArrayList<ItemStack> items = idToStack.get(id);
			for (ItemStack is : items) {
				if (Item.getIdFromItem(item.getItem()) == Item.getIdFromItem(is
						.getItem())) {
					items.remove(is);
					break;
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}    
    
    public static void remove(int id) {
		try {
			@SuppressWarnings("unchecked")
			List<ArrayList<ItemStack>> idToStack = (List<ArrayList<ItemStack>>) OREDICTIONARY_IDTOSTACK.get(null);
            Main.log.info("Clearing " + idToStack.get(id).toString());
			ArrayList<ItemStack> items = idToStack.get(id);
			for (ItemStack is : items) {
                items.remove(is);
                break;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}    

	public static void preInit(){
	}

	public static void init()
	{
        int iron = OreDictionary.getOreID("oreIronHard");
        int gold = OreDictionary.getOreID("oreGoldHard");
        int tin = OreDictionary.getOreID("oreTinHard");
        int copper = OreDictionary.getOreID("oreCopperHard");
        int lead = OreDictionary.getOreID("oreLeadHard");
        int uranium = OreDictionary.getOreID("oreUraniumHard");
        int silver = OreDictionary.getOreID("oreSilverHard");
        int nickel = OreDictionary.getOreID("oreNickelHard");
        int bauxite = OreDictionary.getOreID("oreAluminumHard");
        int diamond = OreDictionary.getOreID("oreDiamondHard");
        
        remove(iron);
        remove(gold);
        remove(tin);
        remove(copper);
        remove(lead);
        remove(uranium);
        remove(silver);
        remove(nickel);
        remove(bauxite);
        remove(diamond);
	}

	public static void postInit() {
	}

}