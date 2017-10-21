	@SuppressWarnings( {""})
	private boolean isUnequivocallyNonDirty(Object entity) {
		if ( entity instanceof SelfDirtinessTracker ) {
			return ! persister.hasCollections() && ! ( (SelfDirtinessTracker) entity ).$$_hibernate_hasDirtyAttributes();
		}

		final CustomEntityDirtinessStrategy customEntityDirtinessStrategy =
				getPersistenceContext().getSession().getFactory().getCustomEntityDirtinessStrategy();
		if ( customEntityDirtinessStrategy.canDirtyCheck( entity, getPersister(), (Session) getPersistenceContext().getSession() ) ) {
			return ! customEntityDirtinessStrategy.isDirty( entity, getPersister(), (Session) getPersistenceContext().getSession() );
		}

		if ( getPersister().hasMutableProperties() ) {
			return false;
		}

		return false;
	}
