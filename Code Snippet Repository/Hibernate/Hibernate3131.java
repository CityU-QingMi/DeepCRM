		@Override
		public final T load(Serializable id) {
			CacheMode sessionCacheMode = getCacheMode();
			boolean cacheModeChanged = false;
			if ( cacheMode != null ) {
				// naive check for now...
				// todo : account for "conceptually equal"
				if ( cacheMode != sessionCacheMode ) {
					setCacheMode( cacheMode );
					cacheModeChanged = true;
				}
			}

			try {
				return doLoad( id );
			}
			finally {
				if ( cacheModeChanged ) {
					// change it back
					setCacheMode( sessionCacheMode );
				}
			}
		}
