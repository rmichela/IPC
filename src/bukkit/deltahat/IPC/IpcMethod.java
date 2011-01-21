package bukkit.deltahat.IPC;

import org.bukkit.plugin.Plugin;

public abstract class IpcMethod{

	private String _pluginName;
	private String _methodName;
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		IpcMethod o = (IpcMethod)obj;
		return _pluginName == o._pluginName && _methodName == o._methodName;
	}

	@Override
	public int hashCode() {
		return _pluginName.hashCode() ^ _methodName.hashCode();
	}
	
	public IpcMethod(String pluginName, String methodName) {
		_pluginName = pluginName;
		_methodName = methodName;
	}
	
	public IpcMethod(Plugin plugin, String methodName) {
		this(plugin.getDescription().getName(), methodName);
	}
	
	public final String getPluginName() {
		return _pluginName;
	}
	
	public final String getMethodName() {
		return _methodName;
	}
	
	/**
	 * Implement this method with the actual implementation of your remote method.
	 * @param args An array of arguments as sent by the calling plugin.
	 * @return
	 */
	protected abstract IpcMethodResult invokeImpl(Object[] args);
	
	@SuppressWarnings("unchecked")
	public final <T> T invoke(Object ... args) throws Exception {
		IpcMethodResult result = invokeImpl(args);
		if(result.invocationSucceeded()) {
			return (T)result.getResult();
		} else {
			throw result.getException();
		}
	}
	
	public final void invokeNoReturn(Object ... args) throws Exception {
		IpcMethodResult result = invokeImpl(args);
		if(!result.invocationSucceeded()) {
			throw result.getException();
		}
	}
	
	String getDictionaryKey() {
		return getDictionaryKey(_pluginName, _methodName);
	}
	
	static String getDictionaryKey(String pluginName, String methodName) {
		return pluginName + ":" + methodName;
	}	
}
