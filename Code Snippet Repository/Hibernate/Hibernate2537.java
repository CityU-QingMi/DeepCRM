	@Override
	public QueryTranslatorFactory initiateService(
			Map configurationValues,
			ServiceRegistryImplementor registry) {
		final StrategySelector strategySelector = registry.getService( StrategySelector.class );
		final QueryTranslatorFactory factory = strategySelector.resolveDefaultableStrategy(
				QueryTranslatorFactory.class,
				configurationValues.get( QUERY_TRANSLATOR ),
				ASTQueryTranslatorFactory.INSTANCE
		);

		log.debugf( "QueryTranslatorFactory : %s", factory );
		if ( factory instanceof ASTQueryTranslatorFactory ) {
			log.usingAstQueryTranslatorFactory();
		}

		return factory;
	}
