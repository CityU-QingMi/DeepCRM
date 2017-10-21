	@SuppressWarnings("")
	private static ValidatorFactory resolveProvidedFactory(ConfigurationService cfgService) {
		return cfgService.getSetting(
				FACTORY_PROPERTY,
				new ConfigurationService.Converter<ValidatorFactory>() {
					@Override
					public ValidatorFactory convert(Object value) {
						try {
							return ValidatorFactory.class.cast( value );
						}
						catch ( ClassCastException e ) {
							throw new IntegrationException(
									String.format(
											Locale.ENGLISH,
											"ValidatorFactory reference (provided via `%s` setting) was not castable to %s : %s",
											FACTORY_PROPERTY,
											ValidatorFactory.class.getName(),
											value.getClass().getName()
									)
							);
						}
					}
				},
				null
		);
	}
