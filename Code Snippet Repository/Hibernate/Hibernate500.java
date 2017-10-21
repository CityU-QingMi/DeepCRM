	public static MultiTenancyStrategy determineMultiTenancyStrategy(Map properties) {
		final Object strategy = properties.get( Environment.MULTI_TENANT );
		if ( strategy == null ) {
			return MultiTenancyStrategy.NONE;
		}

		if ( MultiTenancyStrategy.class.isInstance( strategy ) ) {
			return (MultiTenancyStrategy) strategy;
		}

		final String strategyName = strategy.toString();
		try {
			return MultiTenancyStrategy.valueOf( strategyName.toUpperCase(Locale.ROOT) );
		}
		catch ( RuntimeException e ) {
			LOG.warn( "Unknown multi tenancy strategy [ " +strategyName +" ], using MultiTenancyStrategy.NONE." );
			return MultiTenancyStrategy.NONE;
		}
	}
