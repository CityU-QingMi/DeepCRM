	void purgeExpiredRegistries() {
		long now = System.currentTimeMillis();
		Iterator<Map.Entry<String, UserRegistrySnapshot>> iterator = this.remoteRegistries.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, UserRegistrySnapshot> entry = iterator.next();
			if (entry.getValue().isExpired(now)) {
				iterator.remove();
			}
		}
	}
