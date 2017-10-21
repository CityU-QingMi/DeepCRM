	@Override
	public void stop() {
		if ( started.compareAndSet( true, false ) ) {
			synchronized ( this ) {
				cacheManager.close();
				cacheManager = null;
			}
		}
		else {
			LOG.attemptToRestopAlreadyStoppedJCacheProvider();
		}
	}
