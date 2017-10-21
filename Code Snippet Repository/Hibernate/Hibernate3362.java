	@SuppressWarnings("")
	private void processConfigXml(
			LoadedConfig loadedConfig,
			MergedSettings mergedSettings,
			StandardServiceRegistryBuilder ssrBuilder) {
		if ( ! mergedSettings.configurationValues.containsKey( SESSION_FACTORY_NAME ) ) {
			// there is not already a SF-name in the merged settings
			final String sfName = loadedConfig.getSessionFactoryName();
			if ( sfName != null ) {
				// but the cfg.xml file we are processing named one..
				mergedSettings.configurationValues.put( SESSION_FACTORY_NAME, sfName );
			}
		}

		mergedSettings.configurationValues.putAll( loadedConfig.getConfigurationValues() );
		ssrBuilder.configure( loadedConfig );
	}
