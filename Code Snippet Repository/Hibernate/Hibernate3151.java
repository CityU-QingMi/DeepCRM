	@Override
	public void refresh(Object entity, LockModeType lockModeType, Map<String, Object> properties) {
		checkOpen();

		final CacheMode previousCacheMode = getCacheMode();
		final CacheMode refreshCacheMode = determineAppropriateLocalCacheMode( properties );

		LockOptions lockOptions = null;
		try {
			setCacheMode( refreshCacheMode );

			if ( !contains( entity ) ) {
				throw exceptionConverter.convert( new IllegalArgumentException( "Entity not managed" ) );
			}

			if ( lockModeType != null ) {
				if ( !LockModeType.NONE.equals( lockModeType) ) {
					checkTransactionNeeded();
				}

				lockOptions = buildLockOptions( lockModeType, properties );
				refresh( entity, lockOptions );
			}
			else {
				refresh( entity );
			}
		}
		catch (MappingException e) {
			throw exceptionConverter.convert( new IllegalArgumentException( e.getMessage(), e ) );
		}
		catch (RuntimeException e) {
			throw exceptionConverter.convert( e, lockOptions );
		}
		finally {
			setCacheMode( previousCacheMode );
		}
	}
