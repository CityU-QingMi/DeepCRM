	@Test
	public void testTopLevelManyToOneManagedNestedIsDetached() {
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

		s = openSession();
		tx = s.beginTransaction();
		Item item1Merged = (Item) s.merge( item1 );

		item1Merged.setCategory( category );
		category.setExampleItem( item1_1 );

		// now item1Merged is managed and it has a nested detached item
		s.merge( item1Merged );
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
