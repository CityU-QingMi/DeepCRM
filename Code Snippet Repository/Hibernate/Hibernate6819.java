	@Test
	public void testManyToOneFromNonPk() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Menu menu = new Menu();
		menu.setOrderNbr( "123" );
		menu.setDefault( "F" );
		s.persist( menu );
		FoodItem foodItem = new FoodItem();
		foodItem.setItem( "Mouse" );
		foodItem.setOrder( menu );
		s.persist( foodItem );
		s.flush();
		s.clear();
		foodItem = ( FoodItem ) s.get( FoodItem.class, foodItem.getId() );
		assertNotNull( foodItem.getOrder() );
		assertEquals( "123", foodItem.getOrder().getOrderNbr() );
		tx.rollback();
		s.close();
	}
