	@Test
    public void testMergeToManagedEntityFillFollowedByInvertUsingPutAll() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

        Map<Object,Object> input = new HashMap<Object,Object>();
        Object mergeEntity1 = new Simple( 1 );
		//
        Object managedEntity1 = 1;
        input.put(mergeEntity1, managedEntity1);
        Object mergeEntity2 = new Simple( 3 );
        Object managedEntity2 = 2;
        input.put(mergeEntity2, managedEntity2);
        cache.putAll(input);

		checkCacheConsistency( cache, 2 );

		assertTrue(cache.containsKey(mergeEntity1));
        assertFalse(cache.containsKey(managedEntity1));
        assertTrue(cache.containsKey(mergeEntity2));
        assertFalse(cache.containsKey(managedEntity2));

        assertTrue(cache.invertMap().containsKey(managedEntity1));
        assertFalse(cache.invertMap().containsKey(mergeEntity1));

        assertTrue(cache.invertMap().containsKey(managedEntity2));
        assertFalse(cache.invertMap().containsKey(mergeEntity2));
    }
