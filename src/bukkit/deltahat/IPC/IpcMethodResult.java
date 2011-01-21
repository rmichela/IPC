package bukkit.deltahat.IPC;

/**
 * @author Ryan
 *
 */
public class IpcMethodResult {

	private boolean _invocationSucceeded;
	private Object _result;
	private Exception _exception;
	
	
	/**
	 * Use this constructor when your remote method returns null.
	 */
	public IpcMethodResult() {
		_invocationSucceeded = true;
	}
	
	/**
	 * Use this constructor when your remote method returns a value.
	 * @param result
	 */
	public IpcMethodResult(Object result) {
		_invocationSucceeded = true;
		_result = result;
	}
	
	/**
	 * Use this constructor when your remote method needs to throw an exception.
	 * @param exception
	 */
	public IpcMethodResult(Exception exception) {
		_invocationSucceeded = false;
		_exception = exception;
	}
	
	public boolean invocationSucceeded() {
		return _invocationSucceeded;
	}
	
	public Object getResult() {
		return _result;
	}
	
	public Exception getException() {
		return _exception;
	}
}
