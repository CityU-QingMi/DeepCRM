	@Override
	@SuppressWarnings("")
	public Object structure(Object item) {
		final CacheEntry entry = (CacheEntry) item;
		final String[] names = persister.getPropertyNames();
		final Map map = new HashMap( names.length + 3, 1f );
		map.put( SUBCLASS_KEY, entry.getSubclass() );
		map.put( VERSION_KEY, entry.getVersion() );
		for ( int i=0; i<names.length; i++ ) {
			map.put( names[i], entry.getDisassembledState()[i] );
		}
		return map;
	}
