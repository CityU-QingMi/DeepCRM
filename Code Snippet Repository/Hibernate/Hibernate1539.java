	@SuppressWarnings({"", ""})
	private static void applyRelationalConstraints(ValidatorFactory factory, ActivationContext activationContext) {
		final ConfigurationService cfgService = activationContext.getServiceRegistry().getService( ConfigurationService.class );
		if ( !cfgService.getSetting( BeanValidationIntegrator.APPLY_CONSTRAINTS, StandardConverters.BOOLEAN, true  ) ) {
			LOG.debug( "Skipping application of relational constraints from legacy Hibernate Validator" );
			return;
		}

		final Set<ValidationMode> modes = activationContext.getValidationModes();
		if ( ! ( modes.contains( ValidationMode.DDL ) || modes.contains( ValidationMode.AUTO ) ) ) {
			return;
		}

		applyRelationalConstraints(
				factory,
				activationContext.getMetadata().getEntityBindings(),
				cfgService.getSettings(),
				activationContext.getServiceRegistry().getService( JdbcServices.class ).getDialect(),
				new ClassLoaderAccessImpl(
						null,
						activationContext.getServiceRegistry().getService( ClassLoaderService.class )
				)
		);
	}
