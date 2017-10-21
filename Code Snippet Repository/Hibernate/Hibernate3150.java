	@Override
	public void lock(Object entity, LockModeType lockModeType, Map<String, Object> properties) {
		checkOpen();
		checkTransactionNeeded();

		if ( !contains( entity ) ) {
			throw new IllegalArgumentException( "entity not in the persistence context" );
		}

		final LockOptions lockOptions = buildLockOptions( lockModeType, properties );
		try {
			buildLockRequest( lockOptions ).lock( entity );
		}
		catch (RuntimeException e) {
			throw exceptionConverter.convert( e, lockOptions );
		}
	}
