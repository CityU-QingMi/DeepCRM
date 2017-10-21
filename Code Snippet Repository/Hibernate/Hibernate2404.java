	protected boolean invokeDeleteLifecycle(EventSource session, Object entity, EntityPersister persister) {
		if ( persister.implementsLifecycle() ) {
			LOG.debug( "Calling onDelete()" );
			if ( ( (Lifecycle) entity ).onDelete( session ) ) {
				LOG.debug( "Deletion vetoed by onDelete()" );
				return true;
			}
		}
		return false;
	}
