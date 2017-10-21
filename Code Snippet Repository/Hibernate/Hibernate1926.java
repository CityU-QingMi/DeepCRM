	@Override
	public void postUpdate(Object entity, Object[] updatedState, Object nextVersion) {
		this.loadedState = updatedState;
		setLockMode( LockMode.WRITE );

		if ( getPersister().isVersioned() ) {
			this.version = nextVersion;
			getPersister().setPropertyValue( entity, getPersister().getVersionProperty(), nextVersion );
		}

		if( entity instanceof SelfDirtinessTracker ) {
			( (SelfDirtinessTracker) entity ).$$_hibernate_clearDirtyAttributes();
		}

		getPersistenceContext().getSession()
				.getFactory()
				.getCustomEntityDirtinessStrategy()
				.resetDirty( entity, getPersister(), (Session) getPersistenceContext().getSession() );
	}
