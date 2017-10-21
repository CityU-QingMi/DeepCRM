	@Test
	public void testTableIdentifiers() {
		Invoice orderJboss = doInHibernateSessionBuilder( this::jboss, session -> {
			Invoice _orderJboss = new Invoice();
			session.save( _orderJboss );
			Assert.assertEquals( Long.valueOf( 1 ), _orderJboss.getId() );
			return _orderJboss;
		} );

		Invoice orderAcme = doInHibernateSessionBuilder( this::acme, session -> {
			Invoice _orderAcme = new Invoice();
			session.save( _orderAcme );
			Assert.assertEquals( Long.valueOf( 1 ), _orderAcme.getId() );
			return _orderAcme;
		} );

		doInHibernateSessionBuilder( this::jboss, session -> {
			session.delete( orderJboss );
		} );

		doInHibernateSessionBuilder( this::acme, session -> {
			session.delete( orderAcme );
		} );

		sessionFactory.getStatistics().clear();
	}
