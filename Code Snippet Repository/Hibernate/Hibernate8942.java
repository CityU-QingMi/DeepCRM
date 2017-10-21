	@Test
	@FailureExpected( jiraKey = "" )
	public void testNestedUnidirOneToManyNoBackrefWithNewElement() {
		Category category1 = new Category();
		category1.setName( "category1 name" );
		SubCategory subCategory1 = new SubCategory();
		subCategory1.setName( "subCategory1 name" );
		category1.getSubCategories().add( subCategory1 );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( category1 );
		tx.commit();
		s.close();

		// get another representation of category1
		s = openSession();
		tx = s.beginTransaction();
		Category category1_1 = (Category) s.get( Category.class, category1.getId() );
		Hibernate.initialize( category1_1.getSubCategories() );
		tx.commit();
		s.close();

		SubCategory subCategory2 = new SubCategory();
		subCategory2.setName( "subCategory2 name" );
		category1_1.getSubCategories().add( subCategory2 );

		Item item = new Item();
		item.setName( "item" );
		category1.setExampleItem( item );
		item.setCategory( category1_1 );

		s = openSession();
		tx = s.beginTransaction();
		Category category1Merged = (Category) s.merge( category1 );
		assertEquals( 2, category1Merged.getSubCategories().size() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		category1 = (Category) s.get( Category.class, category1.getId() );
		assertEquals( 2, category1.getSubCategories().size() );
		tx.commit();
		s.close();

		cleanup();
	}
