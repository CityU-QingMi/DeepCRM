	private ManagedEntity getAssociatedManagedEntity(Object entity) {
		if ( ManagedEntity.class.isInstance( entity ) ) {
			final ManagedEntity managedEntity = (ManagedEntity) entity;
			if ( managedEntity.$$_hibernate_getEntityEntry() == null ) {
				// it is not associated
				return null;
			}
			final AbstractEntityEntry entityEntry = (AbstractEntityEntry) managedEntity.$$_hibernate_getEntityEntry();

			if ( entityEntry.getPersister().isMutable() ) {
				return entityEntry.getPersistenceContext() == persistenceContext
						? managedEntity // it is associated
						: null;
			}
			else {
				// if managedEntity is associated with this EntityEntryContext, then
				// it will have an entry in immutableManagedEntityXref and its
				// holder will be returned.
				return immutableManagedEntityXref != null
						? immutableManagedEntityXref.get( managedEntity )
						: null;
			}
		}
		else {
			return nonEnhancedEntityXref != null
					? nonEnhancedEntityXref.get( entity )
					: null;
		}
	}
