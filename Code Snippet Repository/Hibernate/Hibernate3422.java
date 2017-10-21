	@Override
	public boolean isLoaded(Object entity, String attributeName) {
		// added log message to help with HHH-7454, if state == LoadState,NOT_LOADED, returning true or false is not accurate.
		log.debug( "PersistenceUnitUtil#isLoaded is not always accurate; consider using EntityManager#contains instead" );
		LoadState state = PersistenceUtilHelper.isLoadedWithoutReference( entity, attributeName, cache );
		if ( state == LoadState.LOADED ) {
			return true;
		}
		else if ( state == LoadState.NOT_LOADED ) {
			return false;
		}
		else {
			return PersistenceUtilHelper.isLoadedWithReference(
					entity,
					attributeName,
					cache
			) != LoadState.NOT_LOADED;
		}
	}
