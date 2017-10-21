	@Test
	public void testNestedDiffBasicProperty() {
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

		// change basic property of nested entity
		item1_1.setName( "item1_1 name" );

		// change the nested Item to be the copy with the new name
		item1.getCategory().setExampleItem( item1_1 );

		s = openSession();
		tx = s.beginTransaction();
		Item item1Merged = (Item) s.merge( item1 );
		// the name from the top level item will win.
		assertEquals( item1.getName(), item1Merged.getName() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Item item1Get = (Item) s.get( Item.class, item1.getId() );
		assertEquals( item1.getName(), item1Get.getName() );
		tx.commit();
		s.close();

		cleanup();
	}
