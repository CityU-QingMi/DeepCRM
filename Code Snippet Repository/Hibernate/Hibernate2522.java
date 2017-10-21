	private static Set<String> buildGrammarCategories() {
		HashSet<String> categories = new HashSet<String>();
		categories.addAll(
				Arrays.asList(
						"07", 	// "dynamic SQL error"
						"20",
						"2A", 	// "direct SQL syntax error or access rule violation"
						"37",	// "dynamic SQL syntax error or access rule violation"
						"42",	// "syntax error or access rule violation"
						"65",	// Oracle specific as far as I can tell
						"S0"	// MySQL specific as far as I can tell
				)
		);
		return Collections.unmodifiableSet( categories );
	}
