	@Override
	public void evictAll() throws CacheException {
		// TODO Is this a valid operation on a timestamps cache?
		final Transaction tx = suspend();
		try {
			// Invalidate the local region
			invalidateRegion();
		}
		finally {
			resume( tx );
		}
	}
