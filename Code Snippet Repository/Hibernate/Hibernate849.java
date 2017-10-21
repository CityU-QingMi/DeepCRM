	public static Set<String> splitAndCombine(Set<String> x, String values) {
		if ( x.isEmpty() && (values == null || values.isEmpty()) ) {
			return Collections.emptySet();
		}

		HashSet<String> set = new HashSet<String>();
		set.addAll( x );
		if ( values != null && !values.isEmpty() ) {
			Collections.addAll( set, values.split( "\\s*,\\s*" ) );
		}
		return set;
	}
