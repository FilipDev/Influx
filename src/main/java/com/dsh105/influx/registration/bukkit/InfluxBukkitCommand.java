/*
 * This file is part of Influx.
 *
 * Influx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Influx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Influx.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dsh105.influx.registration.bukkit;

import com.dsh105.influx.Controller;
import com.dsh105.influx.Description;
import com.dsh105.influx.InfluxBukkitManager;
import com.dsh105.influx.InfluxManager;
import com.dsh105.influx.dispatch.BukkitCommandEvent;
import com.dsh105.influx.dispatch.BukkitDispatcher;
import com.dsh105.influx.dispatch.Dispatcher;
import com.dsh105.influx.syntax.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfluxBukkitCommand extends org.bukkit.command.Command implements PluginIdentifiableCommand {

    private final InfluxManager<?> manager;
    private Plugin plugin;
    private CommandExecutor executor;

    public InfluxBukkitCommand(InfluxManager<?> manager, Plugin plugin, CommandExecutor executor, Controller controller) {
        this(manager, plugin, executor,controller.getCommand(), controller.getDescription());
    }

    public InfluxBukkitCommand(InfluxManager<?> manager, Plugin plugin, CommandExecutor executor, Command command, Description description) {
        super(command.getCommandName(), description.getShortDescription(), description.getUsage()[0], new ArrayList<>(command.getAliasNames()));
        this.manager = manager;
        this.plugin = plugin;
        this.executor = executor;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return plugin.isEnabled() && executor.onCommand(sender, this, commandLabel, args);
    }

    public InfluxManager<?> getManager() {
        return manager;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}