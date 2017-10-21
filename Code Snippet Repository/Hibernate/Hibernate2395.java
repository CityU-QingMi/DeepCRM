	protected boolean invokeSaveLifecycle(Object entity, EntityPersister persister, EventSource source) {
		// Sub-insertions should occur before containing insertion so
		// Try to do the callback now
		if ( persister.implementsLifecycle() ) {
			LOG.debug( "Calling onSave()" );
			if ( ((Lifecycle) entity).onSave( source ) ) {
				LOG.debug( "Insertion vetoed by onSave()" );
				return true;
			}
		}
		return false;
	}
