	private Dialect determineDialect(DialectResolutionInfoSource resolutionInfoSource) {
		if ( resolutionInfoSource == null ) {
			throw new HibernateException( "Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set" );
		}

		final DialectResolutionInfo info = resolutionInfoSource.getDialectResolutionInfo();
		final Dialect dialect = dialectResolver.resolveDialect( info );

		if ( dialect == null ) {
			throw new HibernateException(
					"Unable to determine Dialect to use [name=" + info.getDatabaseName() +
							", majorVersion=" + info.getDatabaseMajorVersion() +
							"]; user must register resolver or explicitly set 'hibernate.dialect'"
			);
		}

		return dialect;
	}
