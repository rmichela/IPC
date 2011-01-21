package bukkit.deltahat.IPC;

import java.io.File;

import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * IPC for Bukkit
 *
 * @author deltahat
 */
public class IPC extends JavaPlugin {

    public IPC(PluginLoader pluginLoader, Server instance,
            PluginDescriptionFile desc, File folder, File plugin,
            ClassLoader cLoader) {
        super( pluginLoader,  instance,
                 desc, folder,  plugin,
                 cLoader);
    }

   

    public void onEnable() {
        System.out.println("[IPC] Version " + getDescription().getVersion() + " loaded");
    }
    
    
    public void onDisable() {
        
    }
}
