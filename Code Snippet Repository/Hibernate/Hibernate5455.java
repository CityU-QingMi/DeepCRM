	@Test
	public void testMergeToManagedEntityFillFollowedByModifyValues() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

		Object mergeEntity = new Simple( 1 );
		Object managedEntity = new Simple( 2 );

		cache.put( mergeEntity, managedEntity, true );

		Iterator it = cache.values().iterator();
		try {
			it.remove();
			fail( "should have thrown UnsupportedOperationException" );
		}
		catch ( UnsupportedOperationException ex ) {
			// expected
		}

		try {
			cache.values().remove( managedEntity );
			fail( "should have thrown UnsupportedOperationException" );
		}
		catch ( UnsupportedOperationException ex ) {
			// expected
		}

		Object newmanagedEntity = new Simple( 3 );
		try {
			cache.values().add( newmanagedEntity );
			fail( "should have thrown UnsupportedOperationException" );
		}
		catch ( UnsupportedOperationException ex ) {
			// expected
		}
	}
