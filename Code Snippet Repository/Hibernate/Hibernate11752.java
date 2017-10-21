	@Override
	public void start(final SessionFactoryOptions options, final Properties properties) throws CacheException {
		if ( started.compareAndSet( false, true ) ) {
			synchronized ( this ) {
				this.options = options;
				try {
					this.cacheManager = getCacheManager( properties );
				}
				finally {
					if ( this.cacheManager == null ) {
						started.set( false );
					}
				}
			}
		}
		else {
			LOG.attemptToRestartAlreadyStartedJCacheProvider();
		}
	}
