	private static Set[] transpose(List keys) {
		Set[] result = new Set[( (EntityKey[]) keys.get( 0 ) ).length];
		for ( int j = 0; j < result.length; j++ ) {
			result[j] = new HashSet( keys.size() );
			for ( Object key : keys ) {
				result[j].add( ( (EntityKey[]) key )[j] );
			}
		}
		return result;
	}
