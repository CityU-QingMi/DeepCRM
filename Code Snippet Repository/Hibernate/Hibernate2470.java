	protected Serializable performSaveOrUpdate(SaveOrUpdateEvent event) {
		EntityState entityState = getEntityState(
				event.getEntity(),
				event.getEntityName(),
				event.getEntry(),
				event.getSession()
		);

		switch ( entityState ) {
			case DETACHED:
				entityIsDetached( event );
				return null;
			case PERSISTENT:
				return entityIsPersistent( event );
			default: //TRANSIENT or DELETED
				return entityIsTransient( event );
		}
	}
