	private static Set<String> buildHintsSet() {
		HashSet<String> hints = new HashSet<String>();
		hints.add( HINT_TIMEOUT );
		hints.add( SPEC_HINT_TIMEOUT );
		hints.add( HINT_COMMENT );
		hints.add( HINT_FETCH_SIZE );
		hints.add( HINT_CACHE_REGION );
		hints.add( HINT_CACHEABLE );
		hints.add( HINT_READONLY );
		hints.add( HINT_CACHE_MODE );
		hints.add( HINT_FLUSH_MODE );
		hints.add( HINT_NATIVE_LOCKMODE );
		hints.add( HINT_FETCHGRAPH );
		hints.add( HINT_LOADGRAPH );
		return java.util.Collections.unmodifiableSet( hints );
	}
