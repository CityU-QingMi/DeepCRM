	@Override
	protected Configuration constructAndConfigureConfiguration() {
		Configuration configuration = super.constructAndConfigureConfiguration();
		//tag::basic-custom-type-register-BasicType-example[]
		configuration.registerTypeContributor( (typeContributions, serviceRegistry) -> {
			typeContributions.contributeType( BitSetType.INSTANCE );
		} );
		//end::basic-custom-type-register-BasicType-example[]
		return configuration;
	}
