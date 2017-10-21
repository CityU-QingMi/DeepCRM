	@Override
	public Dialect resolveDialect(DialectResolutionInfo info) {
		for ( DialectResolver resolver : resolvers ) {
			try {
				final Dialect dialect = resolver.resolveDialect( info );
				if ( dialect != null ) {
					return dialect;
				}
			}
			catch ( JDBCConnectionException e ) {
				throw e;
			}
			catch ( Exception e ) {
				LOG.exceptionInSubResolver( e.getMessage() );
			}
		}

		return null;
	}
