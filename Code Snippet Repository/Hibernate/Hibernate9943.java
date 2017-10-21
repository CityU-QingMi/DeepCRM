	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		final String entityName = event.getPersister().getEntityName();
		if ( getEnversService().getEntitiesConfigurations().isVersioned( entityName ) ) {
			checkIfTransactionInProgress( event.getSession() );
			if ( isDetachedEntityUpdate( entityName, event.getOldState() ) ) {
				final AuditProcess auditProcess = getEnversService().getAuditProcessManager().get( event.getSession() );
				auditProcess.cacheEntityState(
						event.getId(),
						entityName,
						event.getPersister().getDatabaseSnapshot( event.getId(), event.getSession() )
				);
			}
		}
		return false;
	}
