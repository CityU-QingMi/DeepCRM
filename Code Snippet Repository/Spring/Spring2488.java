	protected void unregisterBeans() {
		Set<ObjectName> snapshot;
		synchronized (this.registeredBeans) {
			snapshot = new LinkedHashSet<>(this.registeredBeans);
		}
		if (!snapshot.isEmpty()) {
			logger.info("Unregistering JMX-exposed beans");
		}
		for (ObjectName objectName : snapshot) {
			doUnregister(objectName);
		}
	}
