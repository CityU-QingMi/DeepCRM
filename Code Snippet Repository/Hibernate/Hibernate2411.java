	protected final boolean isUpdateNecessary(FlushEntityEvent event) throws HibernateException {

		EntityPersister persister = event.getEntityEntry().getPersister();
		Status status = event.getEntityEntry().getStatus();

		if ( !event.isDirtyCheckPossible() ) {
			return true;
		}
		else {

			int[] dirtyProperties = event.getDirtyProperties();
			if ( dirtyProperties != null && dirtyProperties.length != 0 ) {
				return true; //TODO: suck into event class
			}
			else {
				return hasDirtyCollections( event, persister, status );
			}

		}
	}
