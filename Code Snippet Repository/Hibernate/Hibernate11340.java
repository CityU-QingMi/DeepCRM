	public static <T> boolean checkCollection(Collection<T> list, T... objects) {
		if ( list.size() != objects.length ) {
			return false;
		}
		for ( T obj : objects ) {
			if ( !list.contains( obj ) ) {
				return false;
			}
		}
		return true;
	}
