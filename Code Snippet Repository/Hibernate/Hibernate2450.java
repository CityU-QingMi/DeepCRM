	protected void entityIsTransient(MergeEvent event, Map copyCache) {

		LOG.trace( "Merging transient instance" );

		final Object entity = event.getEntity();
		final EventSource source = event.getSession();

		final String entityName = event.getEntityName();
		final EntityPersister persister = source.getEntityPersister( entityName, entity );

		final Serializable id = persister.hasIdentifierProperty() ?
				persister.getIdentifier( entity, source ) :
				null;
		if ( copyCache.containsKey( entity ) ) {
			persister.setIdentifier( copyCache.get( entity ), id, source );
		}
		else {
			( (MergeContext) copyCache ).put( entity, source.instantiate( persister, id ), true ); //before cascade!
		}
		final Object copy = copyCache.get( entity );

		// cascade first, so that all unsaved objects get their
		// copy created before we actually copy
		//cascadeOnMerge(event, persister, entity, copyCache, Cascades.CASCADE_BEFORE_MERGE);
		super.cascadeBeforeSave( source, persister, entity, copyCache );
		copyValues( persister, entity, copy, source, copyCache, ForeignKeyDirection.FROM_PARENT );

		saveTransientEntity( copy, entityName, event.getRequestedId(), source, copyCache );

		// cascade first, so that all unsaved objects get their
		// copy created before we actually copy
		super.cascadeAfterSave( source, persister, entity, copyCache );
		copyValues( persister, entity, copy, source, copyCache, ForeignKeyDirection.TO_PARENT );

		event.setResult( copy );
	}
