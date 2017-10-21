	@Test
	public void testReplaceManagedEntity() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

		Simple mergeEntity = new Simple( 1 );
		Simple managedEntity = new Simple( 0 );
		cache.put(mergeEntity, managedEntity);

		Simple managedEntityNew = new Simple( 0 );
		try {
			cache.put( mergeEntity, managedEntityNew );
		}
		catch( IllegalArgumentException ex) {
			// expected; cannot replace the managed entity result for a particular merge entity.
		}
	}
