package bukkit.deltahat.IPC;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public abstract class IpcEventListener implements Listener, CustomEventListener {

	private String _eventName;
	private Priority _priority;
	private Plugin _parentPlugin;
	
	public IpcEventListener(String eventName, Priority priority, Plugin parentPlugin) {
		_eventName = eventName;
		_priority = priority;
		_parentPlugin = parentPlugin;
	}
	
	@Override
	public void onCustomEvent(Event event) {
		if(event instanceof IpcEvent) {
			IpcEvent trueEvent = (IpcEvent) event;
			if(trueEvent.getEventName().equals(_eventName)) {
				onEventImpl(trueEvent);
			}
		}

	}
	
	/**
	 * Override this method with this event listener's implementation.
	 * @param event
	 */
	protected abstract void onEventImpl(IpcEvent event);

	/**
	 * Convenience method for registering this listener as a custom event listener with bukkit.
	 */
	public void register() {
		PluginManager pm = _parentPlugin.getServer().getPluginManager();
		
		if(pm != null) {
			pm.registerEvent(Event.Type.CUSTOM_EVENT, this, _priority, _parentPlugin);
    	}
	}
}
