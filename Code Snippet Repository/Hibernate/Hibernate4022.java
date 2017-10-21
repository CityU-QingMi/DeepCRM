	public static JpaMetaModelPopulationSetting determineJpaMetaModelPopulationSetting(Map configurationValues) {
		String setting = ConfigurationHelper.getString(
				AvailableSettings.JPA_METAMODEL_POPULATION,
				configurationValues,
				null
		);
		if ( setting == null ) {
			setting = ConfigurationHelper.getString( AvailableSettings.JPA_METAMODEL_GENERATION, configurationValues, null );
			if ( setting != null ) {
				DeprecationLogger.DEPRECATION_LOGGER.deprecatedSetting(
						AvailableSettings.JPA_METAMODEL_GENERATION,
						AvailableSettings.JPA_METAMODEL_POPULATION
				);
			}
		}
		return JpaMetaModelPopulationSetting.parse( setting );
	}
