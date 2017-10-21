	public EntityEntry addReferenceEntry(
			final Object entity,
			final Status status) {

		((ManagedEntity)entity).$$_hibernate_getEntityEntry().setStatus( status );
		entityEntryContext.addEntityEntry( entity, ((ManagedEntity)entity).$$_hibernate_getEntityEntry() );

		setHasNonReadOnlyEnties( status );
		return ((ManagedEntity)entity).$$_hibernate_getEntityEntry();
	}
