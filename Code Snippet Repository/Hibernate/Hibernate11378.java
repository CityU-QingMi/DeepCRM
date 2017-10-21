	@Override
	public void removeAll() throws CacheException {
		try {
			if (!putValidator.beginInvalidatingRegion()) {
				log.failedInvalidateRegion(region.getName());
			}
			Caches.removeAll(cache);
		}
		finally {
			putValidator.endInvalidatingRegion();
		}
	}
