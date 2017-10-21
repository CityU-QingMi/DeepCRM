	@Test
	public void testManagedEntityAssociatedWithNewAndExistingMergeEntities() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

		session.getTransaction().begin();
		Simple mergeEntity = new Simple( 1 );
		Simple managedEntity = new Simple( 0 );
		cache.put(mergeEntity, managedEntity);
		cache.put( new Simple( 1 ), managedEntity );
	}
