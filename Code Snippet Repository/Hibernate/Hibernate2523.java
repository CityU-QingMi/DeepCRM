	private static Set<String> buildDataCategories() {
		HashSet<String> categories = new HashSet<String>();
		categories.addAll( 
				Arrays.asList(
						"21",	// "cardinality violation"
						"22"	// "data exception"
				)
		);
		return Collections.unmodifiableSet( categories );
	}
