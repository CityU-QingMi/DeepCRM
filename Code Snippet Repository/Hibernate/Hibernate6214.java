	@Test
	@TestForIssue( jiraKey="" )
	public void testDirectIdPropertyAccess() throws Exception {
		Session s = openSession();
		final Transaction transaction = s.beginTransaction();
		Item i = new Item();
		s.persist( i );
		Order o = new Order();
		o.setOrderNumber( 1 );
		o.getItems().add( i );
		s.persist( o );
		transaction.commit();
		s.clear();

		o = ( Order ) s.load( Order.class, 1 );
		assertFalse( Hibernate.isInitialized( o ) );
		o.getOrderNumber();
		// If you mapped with field access, any method, except id, call initializes the proxy
		assertFalse( Hibernate.isInitialized( o ) );
		o.getName();
		assertTrue( Hibernate.isInitialized( o ) );
		s.close();
	}
