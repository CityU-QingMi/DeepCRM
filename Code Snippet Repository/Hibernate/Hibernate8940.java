	@Test
	public void testCascadeFromTransientToNonDirtyRepresentations() {

		Item item1 = new Item();
		item1.setName( "item1" );

		Session s = openSession();
		Transaction tx = session.beginTransaction();
		s.persist( item1 );
		tx.commit();
		s.close();

		// Get another representation of the same Item from a different session.

		s = openSession();
		Item item1_1 = (Item) s.get( Item.class, item1.getId() );
		s.close();

		// item1_1 and item1_2 are unmodified representations of the same persistent entity.
		assertFalse( item1 == item1_1 );
		assertTrue( item1.equals( item1_1 ) );

		// Create a transient entity that references both representations.
		Hoarder hoarder = new Hoarder();
		hoarder.setName( "joe" );
		hoarder.getItems().add( item1 );
		hoarder.setFavoriteItem( item1_1 );

		s = openSession();
		tx = s.beginTransaction();
		hoarder = (Hoarder) s.merge( hoarder );
		assertEquals( 1, hoarder.getItems().size() );
		assertSame( hoarder.getFavoriteItem(), hoarder.getItems().iterator().next() );
		assertEquals( item1.getId(), hoarder.getFavoriteItem().getId() );
		assertEquals( item1.getCategory(), hoarder.getFavoriteItem().getCategory() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		hoarder = (Hoarder) s.merge( hoarder );
		assertEquals( 1, hoarder.getItems().size() );
		assertSame( hoarder.getFavoriteItem(), hoarder.getItems().iterator().next() );
		assertEquals( item1.getId(), hoarder.getFavoriteItem().getId() );
		assertEquals( item1.getCategory(), hoarder.getFavoriteItem().getCategory() );
		tx.commit();
		s.close();

		cleanup();
	}
