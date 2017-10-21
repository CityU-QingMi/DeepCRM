	@Test
	public void testSingleIterableOfSingletonCollection() {
		final String str = "a string";
		Set<String> singleTonSet = new HashSet<String>( 1 );
		singleTonSet.add( str );
		List<Iterable<String>> iterableSets = new ArrayList<Iterable<String>>(  );
		iterableSets.add( singleTonSet );
		Iterable<String> iterable = new JoinedIterable<String>( iterableSets );
		assertTrue( iterable.iterator().hasNext() );
		assertSame( str, iterable.iterator().next() );
		assertFalse( iterable.iterator().hasNext() );
		try {
			iterable.iterator().next();
			fail( "Should have thrown NoSuchElementException because the underlying collection is empty.");
		}
		catch ( NoSuchElementException ex ) {
			// expected
		}
		for ( String s : iterable ) {
			fail( "should not have entered loop because underlying iterator should have been exhausted." );
		}
		assertEquals( 1, singleTonSet.size() );
		iterable = new JoinedIterable<String>( iterableSets );
		for ( String s : iterable ) {
			assertSame( str, s );
			iterable.iterator().remove();
		}
		assertTrue( singleTonSet.isEmpty() );
	}
