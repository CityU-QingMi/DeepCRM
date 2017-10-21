	private static SchemaNameResolver determineAppropriateResolverDelegate() {
		// unfortunately Connection#getSchema is only available in Java 1.7 and above
		// and Hibernate still baselines on 1.6.  So for now, use reflection and
		// leverage the Connection#getSchema method if it is available.
		final Class<Connection> jdbcConnectionClass = Connection.class;
		try {
			final Method getSchemaMethod = jdbcConnectionClass.getMethod( "getSchema" );
			if ( getSchemaMethod != null ) {
				if ( getSchemaMethod.getReturnType().equals( String.class ) ) {
					return new SchemaNameResolverJava17Delegate( getSchemaMethod );
				}
			}
		}
		catch (Exception ignore) {
		}

		log.debugf( "Unable to use Java 1.7 Connection#getSchema" );
		return SchemaNameResolverFallbackDelegate.INSTANCE;
	}
