	@Override
	public Dialect buildDialect(Map configValues, DialectResolutionInfoSource resolutionInfoSource) throws HibernateException {
		final Object dialectReference = configValues.get( AvailableSettings.DIALECT );
		if ( !isEmpty( dialectReference ) ) {
			return constructDialect( dialectReference );
		}
		else {
			return determineDialect( resolutionInfoSource );
		}
	}
