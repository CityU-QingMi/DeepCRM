	protected boolean invokeUpdateLifecycle(Object entity, EntityPersister persister, EventSource source) {
		if ( persister.implementsLifecycle() ) {
			LOG.debug( "Calling onUpdate()" );
			if ( ( (Lifecycle) entity ).onUpdate( source ) ) {
				LOG.debug( "Update vetoed by onUpdate()" );
				return true;
			}
		}
		return false;
	}
