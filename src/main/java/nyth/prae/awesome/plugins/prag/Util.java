package nyth.prae.awesome.plugins.prag;

import nyth.prae.awesome.plugins.prag.enums.HunterDamageType;
import nyth.prae.awesome.plugins.prag.enums.GameType;

import java.util.concurrent.TimeUnit;

public class Util {

    public static void setAndSaveConfig(String s, Object v) {

        Prag.instance.getConfig().set(s, v);
        Prag.settingsCache = new SettingsCache();
        Prag.settingsCache.adventureMode = Prag.instance.getConfig().getBoolean("Adventure-Mode");
        Prag.settingsCache.gameTime = Prag.instance.getConfig().getInt("Game-Time");
        Prag.settingsCache.hunterDamage = Prag.instance.getConfig().getDouble("Hunter-Damage");
        Prag.settingsCache.hunterDamageType = HunterDamageType.valueOf(Prag.instance.getConfig().getString("Hunter-Damage-Type"));
        Prag.settingsCache.preparationTime = Prag.instance.getConfig().getInt("Preparation-Time");
        Prag.settingsCache.tagType = GameType.valueOf(Prag.instance.getConfig().getString("Tag-Type"));
        Prag.instance.saveConfig();
        Prag.instance.reloadConfig();
    }

    public static String displayTimer(int sec) {
        int hours = (int) TimeUnit.SECONDS.toHours(sec);
        int minutes = (int) (TimeUnit.SECONDS.toMinutes(sec) - TimeUnit.HOURS.toMinutes(hours));
        int seconds = (int) (TimeUnit.SECONDS.toSeconds(sec) - TimeUnit.MINUTES.toSeconds(minutes));
        if (seconds < 0) {
            seconds = 0;
        }
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
