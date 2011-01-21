package bukkit.deltahat.IPC;

public class IpcRegistry {
	private IpcRegistry() {}
	
	private static IpcMethodCollection _ipcMethods = new IpcMethodCollection();
    
	/**
	 * Gets an IPC method for a given plugin name and method name. If the plugin/method combination registred has
	 * not been registered, an IPC method is generated that returns an appropriate error when invoked.
	 * @param pluginName
	 * @param methodName
	 * @throws IpcMethodException Thrown if pluginName/methodName have been registered more than once or not at all.
	 * @return
	 */
	public static IpcMethod getIpcMethod(String pluginName, String methodName) throws IpcMethodException {
		if(_ipcMethods.containsRemoteMethod(pluginName, methodName)) {
			return _ipcMethods.getOneRemoteMethod(pluginName, methodName);
		} else {
			throw new IpcMethodException(pluginName + "." + methodName + " is not a registered IPC Method.");
		}
	}
	
	/**
	 * Gets an array of IPC methods for a given method name. Each plugin can implement methodName
	 * multiple times.
	 * @param methodName
	 * @return
	 * @throws IpcMethodException Thrown if no plugins implement methodName.
	 */
	public static IpcMethod[] getAllIpcMethods(String methodName) throws IpcMethodException{
		IpcMethod[] ret = _ipcMethods.getAllRemoteMethods(methodName);
		if(ret.length == 0)
		{
			throw new IpcMethodException(methodName + " is not a registered IPC Method.");
		}
		else
		{
			return ret;
		}
	}
	
    /**
     * Publishes an ipc method for invocation by other plugins.
     * @param method The RemoteMethod to publish
     * @throws IllegalArgumentException Exception thrown in the event of a duplicate registration.
     */
    public static void publishIpcMethod(IpcMethod method) throws IllegalArgumentException {
    	_ipcMethods.addRemoteMethod(method);
    }
    
    /**
     * Retracts a previously published ipc method so that other plugins can no longer invoke it.
     * Does not throw an error if called twice.
     * @param method
     */
    public static void retractIpcMethod(IpcMethod method) {
    	if(_ipcMethods.containsRemoteMethod(method)) {
    		_ipcMethods.removeRemoteMethod(method);
    	}
    }
}
