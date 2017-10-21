	@Test
	public void testMergeToManagedEntityFillFollowedByIterateEntrySet() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

		Object mergeEntity = new Simple( 1 );
		Object managedEntity = new Simple( 2 );

		cache.put( mergeEntity, managedEntity, true );

		checkCacheConsistency( cache, 1 );

		Iterator it = cache.entrySet().iterator();
		assertTrue( it.hasNext() );
		Map.Entry entry = ( Map.Entry ) it.next();
		assertSame( mergeEntity, entry.getKey() );
		assertSame( managedEntity, entry.getValue() );
		assertFalse( it.hasNext() );

	}
