	@Override
	public final Dialect resolveDialect(DialectResolutionInfo info) {
		final String databaseName = info.getDatabaseName();
		final int databaseMajorVersion = info.getDatabaseMajorVersion();
		final int databaseMinorVersion = info.getDatabaseMinorVersion();

		if ( nameToMatch.equalsIgnoreCase( databaseName )
				&& ( majorVersionToMatch == NO_VERSION || majorVersionToMatch == databaseMajorVersion )
				&& ( minorVersionToMatch == NO_VERSION || majorVersionToMatch == databaseMinorVersion ) ) {
			try {
				return (Dialect) dialectClass.newInstance();
			}
			catch ( HibernateException e ) {
				// conceivable that the dialect ctor could throw HibernateExceptions, so don't re-wrap
				throw e;
			}
			catch ( Throwable t ) {
				throw new HibernateException(
						"Could not instantiate specified Dialect class [" + dialectClass.getName() + "]",
						t
				);
			}
		}

		return null;
	}
