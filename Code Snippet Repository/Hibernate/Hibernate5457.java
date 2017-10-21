	@Test
	public void testMergeToManagedEntityFillFollowedByModifyValueOfEntrySetElement() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

		Simple mergeEntity = new Simple( 1 );
		Simple managedEntity = new Simple( 0 );
		cache.put(mergeEntity, managedEntity, true);

		Map.Entry entry = (Map.Entry) cache.entrySet().iterator().next();
		( ( Simple ) entry.getValue() ).setValue( 2 );
		assertEquals( 2, managedEntity.getValue() );

		checkCacheConsistency( cache, 1 );

		entry = (Map.Entry) cache.entrySet().iterator().next();
		assertSame( mergeEntity, entry.getKey() );
		assertSame( managedEntity, entry.getValue() );
	}
