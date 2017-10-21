	private void doIntegration(
			Map properties,
			JaccPermissionDeclarations permissionDeclarations,
			SessionFactoryServiceRegistry serviceRegistry) {
		boolean isSecurityEnabled = properties.containsKey( AvailableSettings.JACC_ENABLED );
		if ( ! isSecurityEnabled ) {
			log.debug( "Skipping JACC integration as it was not enabled" );
			return;
		}

		final String contextId = (String) properties.get( AvailableSettings.JACC_CONTEXT_ID );
		if ( contextId == null ) {
			throw new IntegrationException( "JACC context id must be specified" );
		}

		final JaccService jaccService = serviceRegistry.getService( JaccService.class );
		if ( jaccService == null ) {
			throw new IntegrationException( "JaccService was not set up" );
		}

		if ( permissionDeclarations != null ) {
			for ( GrantedPermission declaration : permissionDeclarations.getPermissionDeclarations() ) {
				jaccService.addPermission( declaration );
			}
		}

		final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
		eventListenerRegistry.addDuplicationStrategy( DUPLICATION_STRATEGY );

		eventListenerRegistry.prependListeners( EventType.PRE_DELETE, new JaccPreDeleteEventListener() );
		eventListenerRegistry.prependListeners( EventType.PRE_INSERT, new JaccPreInsertEventListener() );
		eventListenerRegistry.prependListeners( EventType.PRE_UPDATE, new JaccPreUpdateEventListener() );
		eventListenerRegistry.prependListeners( EventType.PRE_LOAD, new JaccPreLoadEventListener() );
	}
