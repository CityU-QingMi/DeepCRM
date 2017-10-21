	@SuppressWarnings("")
	public StandardServiceRegistry build() {
		applyServiceContributingIntegrators();
		applyServiceContributors();

		final Map settingsCopy = new HashMap();
		settingsCopy.putAll( settings );
		settingsCopy.put( org.hibernate.boot.cfgxml.spi.CfgXmlAccessService.LOADED_CONFIG_KEY, aggregatedCfgXml );
		Environment.verifyProperties( settingsCopy );
		ConfigurationHelper.resolvePlaceHolders( settingsCopy );

		return new StandardServiceRegistryImpl(
				autoCloseRegistry,
				bootstrapServiceRegistry,
				initiators,
				providedServices,
				settingsCopy
		);
	}
