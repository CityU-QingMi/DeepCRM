	protected  <T> QueryImplementor<T> buildQueryFromName(String name, Class<T> resultType) {
		try {
			checkOpen();
			checkTransactionSynchStatus();
			delayedAfterCompletion();

			// todo : apply stored setting at the JPA Query level too

			final NamedQueryDefinition namedQueryDefinition = getFactory().getNamedQueryRepository().getNamedQueryDefinition( name );
			if ( namedQueryDefinition != null ) {
				return createQuery( namedQueryDefinition, resultType );
			}

			final NamedSQLQueryDefinition nativeQueryDefinition = getFactory().getNamedQueryRepository().getNamedSQLQueryDefinition( name );
			if ( nativeQueryDefinition != null ) {
				return (QueryImplementor<T>) createNativeQuery( nativeQueryDefinition, resultType );
			}

			throw exceptionConverter.convert( new IllegalArgumentException( "No query defined for that name [" + name + "]" ) );
		}
		catch (RuntimeException e) {
			throw !( e instanceof IllegalArgumentException ) ? new IllegalArgumentException( e ) : e;
		}
	}
