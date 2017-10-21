	@Test
	public void testSingleEmptyIterable() {
		Set<String> emptyList = new HashSet<String>();
		List<Iterable<String>> iterableSets = new ArrayList<Iterable<String>>(  );
		iterableSets.add( emptyList );
		Iterable<String> iterable = new JoinedIterable<String>( iterableSets );
		assertFalse( iterable.iterator().hasNext() );
		try {
			iterable.iterator().next();
			fail( "Should have thrown NoSuchElementException because the underlying collection is empty.");
		}
		catch ( NoSuchElementException ex ) {
			// expected
		}
		try {
			iterable.iterator().remove();
			fail( "Should have thrown IllegalStateException because the underlying collection is empty." );
		}
		catch ( IllegalStateException ex ) {
			// expected
		}
		for ( String s : iterable ) {
			fail( "Should not have entered loop because underlying collection is empty");
		}
	}
