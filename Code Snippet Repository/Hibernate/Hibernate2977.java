	@Override
	public NativeQueryImplementor getNamedNativeQuery(String name) {
		checkOpen();
		checkTransactionSynchStatus();
		delayedAfterCompletion();

		final NamedSQLQueryDefinition nativeQueryDefinition = factory.getNamedQueryRepository().getNamedSQLQueryDefinition( name );
		if ( nativeQueryDefinition != null ) {
			return createNativeQuery( nativeQueryDefinition, true );
		}

		throw exceptionConverter.convert( new IllegalArgumentException( "No query defined for that name [" + name + "]" ) );
	}
