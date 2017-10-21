	public JmxServiceImpl(Map configValues) {
		usePlatformServer = ConfigurationHelper.getBoolean( AvailableSettings.JMX_PLATFORM_SERVER, configValues );
		agentId = (String) configValues.get( AvailableSettings.JMX_AGENT_ID );
		defaultDomain = (String) configValues.get( AvailableSettings.JMX_DOMAIN_NAME );
		sessionFactoryName = ConfigurationHelper.getString(
				AvailableSettings.JMX_SF_NAME,
				configValues,
				ConfigurationHelper.getString( Environment.SESSION_FACTORY_NAME, configValues )
		);
	}
