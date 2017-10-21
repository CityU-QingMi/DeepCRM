	@Test
	public void testMergeToManagedEntityFillFollowedByModifyKeyOfEntrySetElement() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

		Simple mergeEntity = new Simple( 1 );
		Simple managedEntity = new Simple( 0 );
		cache.put(mergeEntity, managedEntity, true);

		Map.Entry entry = (Map.Entry) cache.entrySet().iterator().next();
		( ( Simple ) entry.getKey() ).setValue( 2 );
		assertEquals( 2, mergeEntity.getValue() );

		checkCacheConsistency( cache, 1 );

		entry = (Map.Entry) cache.entrySet().iterator().next();
		assertSame( mergeEntity, entry.getKey() );
		assertSame( managedEntity, entry.getValue() );
	}
