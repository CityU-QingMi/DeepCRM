	@Override
	public void configure(Map configurationValues) {
		if ( configurationValues.containsKey( LEGACY_AUTO_REGISTER ) ) {
			log.debugf(
					"Encountered deprecated Envers setting [%s]; use [%s] or [%s] instead",
					LEGACY_AUTO_REGISTER,
					INTEGRATION_ENABLED,
					EnversIntegrator.AUTO_REGISTER
			);
		}
		this.integrationEnabled = ConfigurationHelper.getBoolean( INTEGRATION_ENABLED, configurationValues, true );
		log.infof( "Envers integration enabled? : %s", integrationEnabled );
	}
