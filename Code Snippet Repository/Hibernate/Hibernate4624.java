	private static Object determineStrategySelection(Map configurationValues) {
		final Object coordinatorStrategy = configurationValues.get( AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY );
		if ( coordinatorStrategy != null ) {
			return coordinatorStrategy;
		}

		final Object legacySetting = configurationValues.get( LEGACY_SETTING_NAME );
		if ( legacySetting != null ) {
			DeprecationLogger.DEPRECATION_LOGGER.logDeprecatedTransactionFactorySetting(
					LEGACY_SETTING_NAME,
					AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY
			);
			return legacySetting;
		}

		// triggers the default
		return null;
	}
