	@Override
	public void registerInsertedKey(EntityPersister persister, Serializable id) {
		// we only are worried about registering these if the persister defines caching
		if ( persister.hasCache() ) {
			if ( insertedKeysMap == null ) {
				insertedKeysMap = new HashMap<>();
			}
			final String rootEntityName = persister.getRootEntityName();
			List<Serializable> insertedEntityIds = insertedKeysMap.get( rootEntityName );
			if ( insertedEntityIds == null ) {
				insertedEntityIds = new ArrayList<>();
				insertedKeysMap.put( rootEntityName, insertedEntityIds );
			}
			insertedEntityIds.add( id );
		}
	}
