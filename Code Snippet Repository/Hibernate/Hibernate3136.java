		@Override
		@SuppressWarnings("")
		public <K extends Serializable> List<T> multiLoad(List<K> ids) {
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
				return entityPersister.multiLoad( ids.toArray( new Serializable[ ids.size() ] ), SessionImpl.this, this );
			}
			finally {
				if ( cacheModeChanged ) {
					// change it back
					setCacheMode( sessionCacheMode );
				}
			}
		}
