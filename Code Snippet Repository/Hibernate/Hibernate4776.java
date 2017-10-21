	@SuppressWarnings("")
	private void configure(MetadataBuilder metadataBuilder, StandardServiceRegistry serviceRegistry) {
		final StrategySelector strategySelector = serviceRegistry.getService( StrategySelector.class );
		if ( implicitNamingStrategy != null ) {
			metadataBuilder.applyImplicitNamingStrategy(
					strategySelector.resolveStrategy( ImplicitNamingStrategy.class, implicitNamingStrategy )
			);
		}
		if ( physicalNamingStrategy != null ) {
			metadataBuilder.applyPhysicalNamingStrategy(
					strategySelector.resolveStrategy( PhysicalNamingStrategy.class, physicalNamingStrategy )
			);
		}
	}
