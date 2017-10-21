	@Test
	@TestForIssue( jiraKey = "")
	public void testContains() {
		Order _order = doInHibernate( this::sessionFactory, session -> {
			Order order = new Order();
			session.persist( order );

			Item item1 = new Item();
			item1.setName( "i1" );
			Item item2 = new Item();
			item2.setName( "i2" );
			order.addItem( item1 );
			order.addItem( item2 );

			return order;
		} );

		doInHibernate( this::sessionFactory, session -> {
			Item item1 = new Item();
			item1.setName( "i1" );

			Item item2 = new Item();
			item2.setName( "i2" );

			assertTrue(_order.getItems().contains( item1 ));
			assertTrue(_order.getItems().contains( item2 ));

			Order order = session.find( Order.class, _order.getId() );

			assertTrue(order.getItems().contains( item1 ));
			assertTrue(order.getItems().contains( item2 ));
		} );

		doInHibernate( this::sessionFactory, session -> {
			Order order = session.find( Order.class, _order.getId() );
			session.delete( order );
		} );
	}
