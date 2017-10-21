	@Test
	public void testCascadeFromDetachedToNonDirtyRepresentations() {
		Item item1 = new Item();
		item1.setName( "item1" );

		Hoarder hoarder = new Hoarder();
		hoarder.setName( "joe" );

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( item1 );
		em.persist( hoarder );
		em.getTransaction().commit();
		em.close();

		// Get another representation of the same Item from a different session.

		em = getOrCreateEntityManager();
		Item item1_1 = em.find( Item.class, item1.getId() );
		em.close();

		// item1_1 and item1_2 are unmodified representations of the same persistent entity.
		assertFalse( item1 == item1_1 );
		assertTrue( item1.equals( item1_1 ) );

		// Update hoarder (detached) to references both representations.
		hoarder.getItems().add( item1 );
		hoarder.setFavoriteItem( item1_1 );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.merge( hoarder );
			fail( "should have failed due IllegalStateException");
		}
		catch (IllegalStateException ex) {
			//expected
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}

		cleanup();
	}
