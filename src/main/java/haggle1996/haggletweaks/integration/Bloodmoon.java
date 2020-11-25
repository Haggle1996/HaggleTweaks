package haggle1996.haggletweaks.integration;

import haggle1996.haggletweaks.HaggleTweaks;
import lumien.randomthings.Handler.Bloodmoon.ServerBloodmoonHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Bloodmoon
{
    public static final String MODID = "HaggleTweaks";
    public static final Logger log = LogManager.getLogger(MODID);

    public static boolean isBloodmoonActive()
    {
        if (!failed)
            try
            {
                if (ServerBloodmoonHandler.INSTANCE != null && ServerBloodmoonHandler.INSTANCE.isBloodmoonActive())
                    return true;
            }
            catch (Throwable t)
            {
                failed = true;

                Bloodmoon.log.info("Disabled Bloodmoon integration due to following error.");
                Bloodmoon.log.catching(Level.INFO, t);
            }
        return false;
    }

    private static boolean failed = false;

}
