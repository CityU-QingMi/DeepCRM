	private void resolveLegacyLimitHandlerBehavior(ServiceRegistry serviceRegistry) {
		// HHH-11194
		// Temporary solution to set whether legacy limit handler behavior should be used.
		final ConfigurationService configurationService = serviceRegistry.getService( ConfigurationService.class );
		legacyLimitHandlerBehavior = configurationService.getSetting(
				AvailableSettings.USE_LEGACY_LIMIT_HANDLERS,
				StandardConverters.BOOLEAN,
				false
		);
	}
