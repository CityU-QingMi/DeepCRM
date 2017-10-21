	private Object[] getValues(Object entity, EntityEntry entry, boolean mightBeDirty, SessionImplementor session) {
		final Object[] loadedState = entry.getLoadedState();
		final Status status = entry.getStatus();
		final EntityPersister persister = entry.getPersister();

		final Object[] values;
		if ( status == Status.DELETED ) {
			//grab its state saved at deletion
			values = entry.getDeletedState();
		}
		else if ( !mightBeDirty && loadedState != null ) {
			values = loadedState;
		}
		else {
			checkId( entity, persister, entry.getId(), session );

			// grab its current state
			values = persister.getPropertyValues( entity );

			checkNaturalId( persister, entry, values, loadedState, session );
		}
		return values;
	}
