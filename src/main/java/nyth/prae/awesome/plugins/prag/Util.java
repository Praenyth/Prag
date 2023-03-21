package nyth.prae.awesome.plugins.prag;

import nyth.prae.awesome.plugins.prag.enums.HunterDamageType;
import nyth.prae.awesome.plugins.prag.enums.GameType;

public class Util {

    public static void setAndSaveConfig(String s, Object v) {

        Prag.instance.getConfig().set(s, v);
        Prag.settings = new Settings();
        Prag.settings.adventureMode = Prag.instance.getConfig().getBoolean("Adventure-Mode");
        Prag.settings.gameTime = Prag.instance.getConfig().getInt("Game-Time");
        Prag.settings.hunterDamage = Prag.instance.getConfig().getDouble("Hunter-Damage");
        Prag.settings.hunterDamageType = HunterDamageType.valueOf(Prag.instance.getConfig().getString("Hunter-Damage-Type"));
        Prag.settings.preparationTime = Prag.instance.getConfig().getInt("Preparation-Time");
        Prag.settings.tagType = GameType.valueOf(Prag.instance.getConfig().getString("Tag-Type"));
        Prag.instance.saveConfig();
        Prag.instance.reloadConfig();
    }

}
