	private SchemaFilterProvider getSchemaFilterProvider(Map options) {
		final Object configuredOption = (options == null)
				? null
				: options.get( AvailableSettings.HBM2DDL_FILTER_PROVIDER );
		return serviceRegistry.getService( StrategySelector.class ).resolveDefaultableStrategy(
				SchemaFilterProvider.class,
				configuredOption,
				DefaultSchemaFilterProvider.INSTANCE
		);
	}
