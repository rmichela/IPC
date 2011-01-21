package bukkit.deltahat.IPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class IpcMethodCollection {
	private HashMap<String, ArrayList<IpcMethod>> _remoteMethods = new HashMap<String, ArrayList<IpcMethod>>();
	
	public void addRemoteMethod(IpcMethod method) {
		if(!_remoteMethods.containsKey(method.getDictionaryKey())) {
			_remoteMethods.put(method.getDictionaryKey(), new ArrayList<IpcMethod>());
		}
		_remoteMethods.get(method.getDictionaryKey()).add(method);
	}
	
	public IpcMethod getOneRemoteMethod(String pluginName, String methodName) throws IpcMethodException{
		if(_remoteMethods.get(IpcMethod.getDictionaryKey(pluginName, methodName)).size() > 1) {
			throw new IpcMethodException("Remote method " + pluginName + ":" + methodName + " has multiple registered implementations.");
		}
		return _remoteMethods.get(IpcMethod.getDictionaryKey(pluginName, methodName)).get(0);
	}
	
	public IpcMethod[] getAllRemoteMethods(String methodName) {
		ArrayList<IpcMethod> ret = new ArrayList<IpcMethod>();
		
		for(Entry<String, ArrayList<IpcMethod>> e : _remoteMethods.entrySet())
		{
			if(e.getKey().contains(methodName))
			{
				ret.addAll(e.getValue());
			}
		}
		
		return ret.toArray(new IpcMethod[] {});
	}
	
	public boolean containsRemoteMethod(String pluginName, String methodName) {
		return _remoteMethods.containsKey(IpcMethod.getDictionaryKey(pluginName, methodName));
	}
	
	public boolean containsRemoteMethod(IpcMethod remoteMethod) {
		return _remoteMethods.containsKey(remoteMethod.getDictionaryKey());
	}
	
	public void removeRemoteMethod(IpcMethod method) {
		_remoteMethods.remove(method.getDictionaryKey());
	}
}
