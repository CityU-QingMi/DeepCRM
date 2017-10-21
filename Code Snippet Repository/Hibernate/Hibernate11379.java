	@Override
	public void evictAll() throws CacheException {
		try {
			if (!putValidator.beginInvalidatingRegion()) {
				log.failedInvalidateRegion(region.getName());
			}

			// Invalidate the local region and then go remote
			region.invalidateRegion();
			Caches.broadcastEvictAll(cache);
		}
		finally {
			putValidator.endInvalidatingRegion();
		}
	}
