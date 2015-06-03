package com.jpmc.cca.util;

import java.util.TreeMap;

import com.jpmc.cca.bean.Message;
import com.jpmc.cca.external.Resource;

/**
 * This is a singleton class which maintians information about all the External
 * Resources available, if any new Resources are need to be added, just
 * implement the Interface {@link Resource}, and register the same in
 * configuration file.
 * 
 * @author Prashanth N
 *
 */
public class Gateway {

	private static Object lock = new Object();

	private TreeMap<String, Resource> availableResource, busyResource;

	private static Gateway pool;

	private void registerResource(Resource resource) {
		availableResource.put(resource.getInfo(), resource);
	}

	private Gateway(String[] resources) {
		availableResource = new TreeMap<String, Resource>();
		busyResource = new TreeMap<String, Resource>();
		for (int i = 0; i < resources.length; i++) {
			try {
				Resource instance = (Resource) Class.forName(resources[i])
						.newInstance();
				registerResource(instance);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static Gateway getGateway() {
		if (pool == null) {
			synchronized (lock) {
				if (pool == null) {
					Config config = Config.getConfig();
					String resources = config.getProperty("resources", "");
					pool = new Gateway(resources.split(","));
				}
			}
		}
		return pool;
	}

	public boolean isResourceAvailable() {
		return !availableResource.isEmpty();
	}

	private class Executor implements Runnable {
		Message message;

		public Executor(Message message) {
			this.message = message;
		}

		public void run() {
			Resource resource = getResource();
			resource.send(message);
			free(resource);
			message.completed();
		}
	}

	public void push(Message message) {
		System.out.println("Received Message " + message.toString());
		Executor executor = new Executor(message);
		new Thread(executor).start();
	}

	private Resource getResource() {
		Resource existingResource = (Resource) availableResource.lastEntry()
				.getValue();
		availableResource.remove(existingResource.getInfo());
		busyResource.put(existingResource.getInfo(), existingResource);
		return existingResource;
	}

	private synchronized void free(Resource resource) {

		busyResource.remove(resource.getInfo());
		availableResource.put(resource.getInfo(), resource);
		// Wake up threads that are waiting for a connection
		notify();
	}
}
