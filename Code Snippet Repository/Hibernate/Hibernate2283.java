	public void addBatchLoadableEntityKey(EntityKey key) {
		if ( key.isBatchLoadable() ) {
			LinkedHashSet<EntityKey> set =  batchLoadableEntityKeys.get( key.getEntityName());
			if (set == null) {
				set = new LinkedHashSet<>( 8 );
				batchLoadableEntityKeys.put( key.getEntityName(), set);
			}
			set.add(key);
		}
	}
