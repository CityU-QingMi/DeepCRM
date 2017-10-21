	@Override
	public void remove(SharedSessionContractImplementor session, Object key) throws CacheException {
		putValidator.setCurrentSession(session);
		try {
			// We update whether or not the region is valid. Other nodes
			// may have already restored the region so they need to
			// be informed of the change.
			writeCache.remove(key);
		}
		finally {
			putValidator.resetCurrentSession();
		}
	}
