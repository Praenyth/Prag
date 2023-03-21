package nyth.prae.awesome.plugins.prag.commands.subcommands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import nyth.prae.awesome.plugins.prag.enums.GameType;
import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.HunterDamageType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class PragSettingsCommand {

    //
    // Help commands
    //
    @CommandMethod("prag settings")
    @CommandDescription("Changes the settings of the game!")
    @CommandPermission("prag.admin.settings")
    public void settings(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the options provided by the game! The changes automatically save, so you won't have to worry about that!");
    }

    @CommandMethod("prag settings tagtype")
    @CommandDescription("Changes the tagtype of the game!")
    @CommandPermission("prag.admin.settings")
    public void tagTypeHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the tag game provided by the plugin! The changes automatically save, so you won't have to worry about that!");
    }

    @CommandMethod("prag settings adventuremode")
    @CommandDescription("Changes if the players are in adventure mode or not!")
    @CommandPermission("prag.admin.settings")
    public void adventureModeHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change whether or not the players are in adventure mode or not. The changes automatically save, so you won't have to worry about that!");
    }

    @CommandMethod("prag settings preparationtime")
    @CommandDescription("Changes the time (in seconds) in the preparation period of the game!")
    @CommandPermission("prag.admin.settings")
    public void preparationTimeHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the amount of time (in seconds) of the preparation period! The changes automatically save, so you won't have to worry about that!");
    }

    @CommandMethod("prag settings gametime")
    @CommandDescription("Changes the time (in seconds) in the game period!")
    @CommandPermission("prag.admin.settings")
    public void gameTimeHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the amount of time (in seconds) of the game! The changes automatically save, so you won't have to worry about that!");
    }

    @CommandMethod("prag settings hunterdamagetype")
    @CommandDescription("Changes the damage type that the hunters do to the runners!")
    @CommandPermission("prag.admin.settings")
    public void hunterDamageTypeHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the hunter damage type provided by the plugin! The changes automatically save, so you won't have to worry about that!");
    }

    @CommandMethod("prag settings hunterdamage")
    @CommandDescription("Changes the damage that the hunters do to the runners!")
    @CommandPermission("prag.admin.settings")
    public void hunterDamageHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the amount of damage that the hunters do to the runners! The changes automatically save, so you won't have to worry about that!");
    }

    //
    // Change settings commands
    //
    @CommandMethod("prag settings tagtype <tagtype>")
    @CommandDescription("Changes the tagtype of the game!")
    @CommandPermission("prag.admin.settings")
    public void setTagType(CommandContext<CommandSender> context, @Argument(value = "tagtype") GameType tagType) {

        Util.setAndSaveConfig("Tag-Type", tagType.name());
        context.getSender().sendMessage(ChatColor.GREEN+"The tag type has been changed to "+tagType.name()+"!");
    }

    @CommandMethod("prag settings adventuremode <adventuremode>")
    @CommandDescription("Changes if the players are in adventure mode or not!")
    @CommandPermission("prag.admin.settings")
    public void setAdventureMode(CommandContext<CommandSender> context, @Argument(value = "adventuremode") boolean adventuremode) {
        Util.setAndSaveConfig("Adventure-Mode", adventuremode);
        context.getSender().sendMessage(ChatColor.GREEN+"Adventure mode has been set to "+adventuremode+"!");
    }

    @CommandMethod("prag settings preparationtime <preparationtime>")
    @CommandDescription("Changes the time (in seconds) in the preparation period of the game!")
    @CommandPermission("prag.admin.settings")
    public void setPreparationTime(CommandContext<CommandSender> context, @Argument(value = "preparationtime") int preparationtime) {
        Util.setAndSaveConfig("Preparation-Time", preparationtime);
        context.getSender().sendMessage(ChatColor.GREEN+"The preparation time has been set to "+preparationtime+" seconds!");
    }

    @CommandMethod("prag settings gametime <gametime>")
    @CommandDescription("Changes the time (in seconds) in the game period!")
    @CommandPermission("prag.admin.settings")
    public void setGameTime(CommandContext<CommandSender> context, @Argument(value = "gametime") int gametime) {
        Util.setAndSaveConfig("Game-Time", gametime);
        context.getSender().sendMessage(ChatColor.GREEN+"The game time has been set to "+gametime+" seconds!");
    }

    @CommandMethod("prag settings hunterdamagetype <hunterdamagetype>")
    @CommandDescription("Changes the damage type that the hunters do to the runners!")
    @CommandPermission("prag.admin.settings")
    public void setHunterDamageType(CommandContext<CommandSender> context, @Argument(value = "hunterdamagetype") HunterDamageType hunterDamageType) {
        Util.setAndSaveConfig("Hunter-Damage-Type", hunterDamageType.name());
        context.getSender().sendMessage(ChatColor.GREEN+"The hunter damage type has been set to "+hunterDamageType.name()+"!");
    }

    @CommandMethod("prag settings hunterdamage <hunterdamage>")
    @CommandDescription("Changes the damage that the hunters do to the runners!")
    @CommandPermission("prag.admin.settings")
    public void setHunterDamage(CommandContext<CommandSender> context, @Argument(value = "hunterdamage") double hunterDamage) {
        Util.setAndSaveConfig("Hunter-Damage", hunterDamage);
        context.getSender().sendMessage(ChatColor.GREEN+"The hunter damage type has been set to "+hunterDamage+"!");
    }
}
