package bukkit.deltahat.IPC;

public abstract class IpcException extends Exception {

	private static final long serialVersionUID = 8069783089460274182L;

	public IpcException(String message) {
		super(message);
	}
}
