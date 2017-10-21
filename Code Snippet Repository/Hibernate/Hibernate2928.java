	public static String determineImplicitOptimizerName(int incrementSize, Properties configSettings) {
		if ( incrementSize <= 1 ) {
			return StandardOptimizerDescriptor.NONE.getExternalName();
		}

		// see if the user defined a preferred pooled optimizer...
		final String preferredPooledOptimizerStrategy = configSettings.getProperty( AvailableSettings.PREFERRED_POOLED_OPTIMIZER );
		if ( StringHelper.isNotEmpty( preferredPooledOptimizerStrategy ) ) {
			return preferredPooledOptimizerStrategy;
		}

		// otherwise fallback to the fallback strategy (considering the deprecated PREFER_POOLED_VALUES_LO setting)
		return ConfigurationHelper.getBoolean( AvailableSettings.PREFER_POOLED_VALUES_LO, configSettings, false )
				? StandardOptimizerDescriptor.POOLED_LO.getExternalName()
				: StandardOptimizerDescriptor.POOLED.getExternalName();
	}
