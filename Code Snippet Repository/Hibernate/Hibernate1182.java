	@Override
	@SuppressWarnings("")
	public Object structure(Object item) {
		final CollectionCacheEntry entry = (CollectionCacheEntry) item;
		final Serializable[] state = entry.getState();
		final Map map = new HashMap( state.length );
		int i = 0;
		while ( i < state.length ) {
			map.put( state[i++], state[i++] );
		}
		return map;
	}
