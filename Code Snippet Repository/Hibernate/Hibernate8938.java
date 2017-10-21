	@Test
	public void testNestedManyToOneChangedToNull() {
		Item item1 = new Item();
		item1.setName( "item1 name" );
		Category category = new Category();
		category.setName( "category" );

		item1.setCategory( category );
		category.setExampleItem( item1 );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( item1 );
		tx.commit();
		s.close();

		// get another representation of item1
		s = openSession();
		tx = s.beginTransaction();
		Item item1_1 = (Item) s.get( Item.class, item1.getId() );
		tx.commit();
		s.close();

		// change many-to-one in nested entity to null.
		item1_1.setCategory( null );
		item1.getCategory().setExampleItem( item1_1 );

		s = openSession();
		tx = s.beginTransaction();
		Item item1Merged = (Item) s.merge( item1 );
		// the many-to-one from the top level item will win.
		assertEquals( category.getName(), item1Merged.getCategory().getName() );
		assertSame( item1Merged, item1Merged.getCategory().getExampleItem() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		item1 = (Item) s.get( Item.class, item1.getId() );
		assertEquals( category.getName(), item1.getCategory().getName() );
		assertSame( item1, item1.getCategory().getExampleItem() );
		tx.commit();
		s.close();

		cleanup();
	}
