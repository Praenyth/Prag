package nyth.prae.awesome.plugins.prag.commands.subcommands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.GameType;
import nyth.prae.awesome.plugins.prag.enums.TaggerDamageType;
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

    @CommandMethod("prag settings taggerdamagetype")
    @CommandDescription("Changes the damage type that the taggers do to the runners!")
    @CommandPermission("prag.admin.settings")
    public void taggerDamageTypeHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the tagger damage type provided by the plugin! The changes automatically save, so you won't have to worry about that!");
    }

    @CommandMethod("prag settings taggerdamage")
    @CommandDescription("Changes the damage that the taggers do to the runners!")
    @CommandPermission("prag.admin.settings")
    public void taggerDamageHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the amount of damage that the taggers do to the runners! The changes automatically save, so you won't have to worry about that!");
    }

    @CommandMethod("prag settings taggeramount")
    @CommandDescription("Changes the amount of taggers there are!")
    @CommandPermission("prag.admin.settings")
    public void taggerAmountHelp(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(ChatColor.YELLOW+"This is to change the amount of taggers! The changes automatically save, so you won't have to worry about that!");
    }
    //
    // Change settings commands
    //
    @CommandMethod("prag settings tagtype <tagtype>")
    @CommandDescription("Changes the tagtype of the game!")
    @CommandPermission("prag.admin.settings")
    public void setTagType(CommandContext<CommandSender> context, @Argument(value = "tagtype") GameType tagType) {

        Util.setAndSaveConfig("Tag-Type", tagType.name());
        Prag.settingsCache.tagType = tagType;
        context.getSender().sendMessage(ChatColor.GREEN+"The tag type has been changed to "+tagType.name()+"!");
    }

    @CommandMethod("prag settings preparationtime <preparationtime>")
    @CommandDescription("Changes the time (in seconds) in the preparation period of the game!")
    @CommandPermission("prag.admin.settings")
    public void setPreparationTime(CommandContext<CommandSender> context, @Argument(value = "preparationtime") int preparationtime) {
        Util.setAndSaveConfig("Preparation-Time", preparationtime);
        Prag.settingsCache.preparationTime = preparationtime;
        context.getSender().sendMessage(ChatColor.GREEN+"The preparation time has been set to "+preparationtime+" seconds!");
    }

    @CommandMethod("prag settings gametime <gametime>")
    @CommandDescription("Changes the time (in seconds) in the game period!")
    @CommandPermission("prag.admin.settings")
    public void setGameTime(CommandContext<CommandSender> context, @Argument(value = "gametime") int gametime) {
        Util.setAndSaveConfig("Game-Time", gametime);
        Prag.settingsCache.gameTime = gametime;
        context.getSender().sendMessage(ChatColor.GREEN+"The game time has been set to "+gametime+" seconds!");
    }

    @CommandMethod("prag settings taggerdamagetype <taggerdamagetype>")
    @CommandDescription("Changes the damage type that the taggers do to the runners!")
    @CommandPermission("prag.admin.settings")
    public void setTaggerDamageType(CommandContext<CommandSender> context, @Argument(value = "taggerdamagetype") TaggerDamageType taggerDamageType) {
        Util.setAndSaveConfig("Tagger-Damage-Type", taggerDamageType.name());
        Prag.settingsCache.taggerDamageType = taggerDamageType;
        context.getSender().sendMessage(ChatColor.GREEN+"The tagger damage type has been set to "+taggerDamageType.name()+"!");
    }

    @CommandMethod("prag settings taggerdamage <taggerdamage>")
    @CommandDescription("Changes the damage that the taggers do to the runners!")
    @CommandPermission("prag.admin.settings")
    public void setTaggerDamage(CommandContext<CommandSender> context, @Argument(value = "taggerdamage") double taggerDamage) {
        Util.setAndSaveConfig("Tagger-Damage", taggerDamage);
        Prag.settingsCache.taggerDamage = taggerDamage;
        context.getSender().sendMessage(ChatColor.GREEN+"The tagger damage has been set to "+taggerDamage+"!");
    }

    @CommandMethod("prag settings taggeramount <taggeramount>")
    @CommandDescription("Changes the amount of taggers there are!")
    @CommandPermission("prag.admin.settings")
    public void setTaggerAmount(CommandContext<CommandSender> context, @Argument(value = "taggeramount") int taggerAmount) {
        Util.setAndSaveConfig("Amount-Of-Taggers", taggerAmount);
        Prag.settingsCache.amountOfTaggers = taggerAmount;
        context.getSender().sendMessage(ChatColor.GREEN+"The tagger amount has been set to "+taggerAmount+"!");
    }
}
