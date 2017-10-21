	public static void clearCacheManagers() {
		for (EmbeddedCacheManager manager : cacheManagers.values()) {
			try {
				manager.stop();
			} catch (Exception e) {
				log.error("Exception cleaning up CacheManager " + manager, e);
			}
		}
		cacheManagers.clear();
	}
