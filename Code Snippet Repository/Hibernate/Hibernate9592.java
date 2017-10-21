	@Override
	protected Configuration constructAndConfigureConfiguration() {
		Configuration configuration = super.constructAndConfigureConfiguration();
		configuration.registerTypeContributor( (typeContributions, serviceRegistry) -> {
			typeContributions.contributeType( ArrayType.INSTANCE,
				new String[] {
					  MyList.class.getName(),
					  ArrayType.INSTANCE.getName()
				}
			);
		} );
		return configuration;
	}
