	private void checkTestOrderByResults(
			List results,
			Zoo zoo1,
			Zoo zoo2,
			Zoo zoo3,
			Zoo zoo4,
			Set<Zoo> zoosUnordered) {
		assertEquals( 4, results.size() );
		Set<Zoo> zoosUnorderedCopy = ( zoosUnordered == null ? null : new HashSet<Zoo>( zoosUnordered ) );
		checkTestOrderByResult( results.get( 0 ), zoo1, zoosUnorderedCopy );
		checkTestOrderByResult( results.get( 1 ), zoo2, zoosUnorderedCopy );
		checkTestOrderByResult( results.get( 2 ), zoo3, zoosUnorderedCopy );
		checkTestOrderByResult( results.get( 3 ), zoo4, zoosUnorderedCopy );
		if ( zoosUnorderedCopy != null ) {
			assertTrue( zoosUnorderedCopy.isEmpty() );
		}
	}
