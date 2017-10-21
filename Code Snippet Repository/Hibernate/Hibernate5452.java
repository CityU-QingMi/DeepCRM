	@Test
    public void testMergeToManagedEntityFillFollowedByInvertUsingPutWithSetOperatedOnArg() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

        Object mergeEntity = new Simple( 1 );
        Object managedEntity = new Simple( 2 );
        
        cache.put(mergeEntity, managedEntity, true);

		checkCacheConsistency( cache, 1 );

		assertTrue(cache.containsKey(mergeEntity));
        assertFalse( cache.containsKey( managedEntity ) );

        assertTrue( cache.invertMap().containsKey( managedEntity ) );
        assertFalse( cache.invertMap().containsKey( mergeEntity ) );
        
        cache.clear();

		checkCacheConsistency( cache, 0 );

		cache.put(mergeEntity, managedEntity, false);
		assertFalse( cache.isOperatedOn( mergeEntity ) );

		checkCacheConsistency( cache, 1 );

		assertTrue(cache.containsKey(mergeEntity));
        assertFalse(cache.containsKey(managedEntity));
    }
