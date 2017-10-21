	public static <T> Set<T> makeCopy(Set<T> source) {
		if ( source == null ) {
			return null;
		}

		final int size = source.size();
		final Set<T> copy = new HashSet<T>( size + 1 );
		copy.addAll( source );
		return copy;
	}
