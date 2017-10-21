	public void merge(LoadedConfig incoming) {
		if ( sessionFactoryName != null ) {
			if ( incoming.getSessionFactoryName() != null ) {
				log.debugf(
						"More than one cfg.xml file attempted to supply SessionFactory name: [%s], [%s].  Keeping initially discovered one [%s]",
						getSessionFactoryName(),
						incoming.getSessionFactoryName(),
						getSessionFactoryName()
				);
			}
		}
		else {
			sessionFactoryName = incoming.getSessionFactoryName();
		}

		addConfigurationValues( incoming.getConfigurationValues() );
		addMappingReferences( incoming.getMappingReferences() );
		addCacheRegionDefinitions( incoming.getCacheRegionDefinitions() );
		addJaccPermissions( incoming.getJaccPermissionsByContextId() );
		addEventListeners( incoming.getEventListenerMap() );
	}
