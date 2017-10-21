	@Override
	public Serializable insert(String entityName, Object entity) {
		checkOpen();
		EntityPersister persister = getEntityPersister( entityName, entity );
		Serializable id = persister.getIdentifierGenerator().generate( this, entity );
		Object[] state = persister.getPropertyValues( entity );
		if ( persister.isVersioned() ) {
			boolean substitute = Versioning.seedVersion(
					state,
					persister.getVersionProperty(),
					persister.getVersionType(),
					this
			);
			if ( substitute ) {
				persister.setPropertyValues( entity, state );
			}
		}
		if ( id == IdentifierGeneratorHelper.POST_INSERT_INDICATOR ) {
			id = persister.insert( state, entity, this );
		}
		else {
			persister.insert( id, state, entity, this );
		}
		persister.setIdentifier( entity, id, this );
		return id;
	}
