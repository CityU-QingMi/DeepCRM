	@Override
	public void integrate(
			Metadata metadata,
			SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		doIntegration(
				serviceRegistry.getService( ConfigurationService.class ).getSettings(),
				// pass no permissions here, because atm actually injecting the
				// permissions into the JaccService is handled on SessionFactoryImpl via
				// the org.hibernate.boot.cfgxml.spi.CfgXmlAccessService
				null,
				serviceRegistry
		);
	}
