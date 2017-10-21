	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testMergePersistentEntityWithNewOneToManyElements() {
		Order order = new Order();

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( order );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		order = s.get( Order.class, order.getId() );
		Item item1 = new Item();
		item1.setName( "i1" );
		Item item2 = new Item();
		item2.setName( "i2" );
		order.addItem( item1 );
		order.addItem( item2 );
		order = (Order) s.merge( order );
		//s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		order = s.get( Order.class, order.getId() );
		assertEquals( 2, order.getItems().size() );
		s.delete( order );
		s.getTransaction().commit();
		s.close();
	}
