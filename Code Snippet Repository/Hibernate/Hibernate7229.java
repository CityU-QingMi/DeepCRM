	@Test
	public void testMergeDetachedEntityWithNewOneToManyElements() {
		Order order = new Order();

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( order );
		s.getTransaction().commit();
		s.close();

		Item item1 = new Item();
		item1.name = "i1";

		Item item2 = new Item();
		item2.name = "i2";

		order.addItem( item1 );
		order.addItem( item2 );

		s = openSession();
		s.getTransaction().begin();
		order = (Order) s.merge( order );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		order = s.get( Order.class, order.id );
		assertEquals( 2, order.items.size() );
		s.delete( order );
		s.getTransaction().commit();
		s.close();
	}
