package bukkit.deltahat.IPC;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class IpcEvent extends Event implements Cancellable {
	
	private Object _data;
	private boolean _cancelled;
	private Player _player;

	public IpcEvent(String eventName) {
		this(eventName, null, null);
	}
	
	public IpcEvent(String eventName, Player player) {
		this(eventName, player, null);
	}
	
	public IpcEvent(String eventName, Object data) {
		this(eventName, null, data);
	}
	
	public IpcEvent(String eventName, Player player, Object data) {
		super(eventName);
		_player = player;
		_data = data;
	}
	
	@SuppressWarnings("unchecked")
	public final <T> T getData() {
		return (T)_data;
	}
	
	public final Player getPlayer() {
		return _player;
	}

	@Override
	public final boolean isCancelled() {
		return _cancelled;
	}

	@Override
	public final void setCancelled(boolean cancelled) {
		_cancelled = cancelled;
	}
}
