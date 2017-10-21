	@Override
	public void update(String entityName, Object entity) {
		checkOpen();
		EntityPersister persister = getEntityPersister( entityName, entity );
		Serializable id = persister.getIdentifier( entity, this );
		Object[] state = persister.getPropertyValues( entity );
		Object oldVersion;
		if ( persister.isVersioned() ) {
			oldVersion = persister.getVersion( entity );
			Object newVersion = Versioning.increment( oldVersion, persister.getVersionType(), this );
			Versioning.setVersion( state, newVersion, persister );
			persister.setPropertyValues( entity, state );
		}
		else {
			oldVersion = null;
		}
		persister.update( id, state, null, false, null, oldVersion, entity, null, this );
	}
