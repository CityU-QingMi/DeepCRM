	private static Set<String> buildContraintCategories() {
		HashSet<String> categories = new HashSet<String>();
		categories.addAll(
				Arrays.asList(
						"23",	// "integrity constraint violation"
						"27",	// "triggered data change violation"
						"44"	// "with check option violation"
				)
		);
		return Collections.unmodifiableSet( categories );
	}
