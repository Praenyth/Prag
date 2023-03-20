package nyth.prae.awesome.plugins.prag.commands;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.SimpleCommandMeta;
import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.commands.subcommands.PragStartCommand;
import org.bukkit.command.CommandSender;

import java.util.function.Function;

public class PragCommand {

    public static void register() {
        final BukkitCommandManager commandManager;
        AnnotationParser<CommandSender> annotationParser;
        try {

            commandManager = new BukkitCommandManager(
                    Prag.instance,
                    CommandExecutionCoordinator.simpleCoordinator(),
                    Function.identity(),
                    Function.identity()
            );
            commandManager.registerBrigadier();

            annotationParser = new AnnotationParser<>(
                    commandManager,
                    CommandSender.class,
                    parameters -> SimpleCommandMeta.empty()
            );

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        annotationParser.parse(new PragCommand());
        annotationParser.parse(new PragStartCommand());

    }

    @CommandMethod("prag")
    public void prag(CommandContext<CommandSender> sender) {
        sender.getSender().sendMessage("Hello World");
    }

}
