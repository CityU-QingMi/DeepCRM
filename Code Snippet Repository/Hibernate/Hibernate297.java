	@Override
	protected Configuration constructAndConfigureConfiguration() {
		Configuration configuration = super.constructAndConfigureConfiguration();
		//tag::basic-custom-type-register-UserType-example[]
		configuration.registerTypeContributor( (typeContributions, serviceRegistry) -> {
			typeContributions.contributeType( BitSetUserType.INSTANCE, "bitset");
		} );
		//end::basic-custom-type-register-UserType-example[]
		return configuration;
	}
