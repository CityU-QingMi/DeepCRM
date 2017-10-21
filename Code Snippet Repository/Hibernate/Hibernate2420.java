	private boolean isUpdateNecessary(final FlushEntityEvent event, final boolean mightBeDirty) {
		final Status status = event.getEntityEntry().getStatus();
		if ( mightBeDirty || status == Status.DELETED ) {
			// compare to cached state (ignoring collections unless versioned)
			dirtyCheck( event );
			if ( isUpdateNecessary( event ) ) {
				return true;
			}
			else {
				if ( SelfDirtinessTracker.class.isInstance( event.getEntity() ) ) {
					( (SelfDirtinessTracker) event.getEntity() ).$$_hibernate_clearDirtyAttributes();
				}
				event.getSession()
						.getFactory()
						.getCustomEntityDirtinessStrategy()
						.resetDirty( event.getEntity(), event.getEntityEntry().getPersister(), event.getSession() );
				return false;
			}
		}
		else {
			return hasDirtyCollections( event, event.getEntityEntry().getPersister(), status );
		}
	}
