	public Map getEntries() {
		Map map = new HashMap();
		for ( Object o : region.toMap().entrySet() ) {
			Map.Entry me = (Map.Entry) o;
			Object id;
			if ( entityRegionAccessStrategy != null ) {
				id = entityRegionAccessStrategy.getCacheKeyId( me.getKey() );
			}
			else if ( collectionRegionAccessStrategy != null ) {
				id = collectionRegionAccessStrategy.getCacheKeyId( me.getKey() );
			}
			else {
				id = me.getKey();
			}
			map.put( id, me.getValue() );
		}
		return map;
	}
