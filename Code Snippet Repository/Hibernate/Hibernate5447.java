	@Test
	public void testManagedAssociatedWith2ExistingMergeEntities() {
		MergeContext cache = new MergeContext( session, new DoNothingEntityCopyObserver() );

		session.getTransaction().begin();
		Simple mergeEntity1 = new Simple( 1 );
		session.persist( mergeEntity1 );
		Simple managedEntity1 = new Simple( 1 );
		cache.put( mergeEntity1, managedEntity1 );
		Simple managedEntity2 = new Simple( 2 );

		try {
			cache.put( mergeEntity1, managedEntity2 );
			fail( "should have thrown IllegalArgumentException");
		}
		catch( IllegalArgumentException ex ) {
			// expected; cannot change managed entity associated with a merge entity
		}
		finally {
			session.getTransaction().rollback();
		}
	}
