	@Test
	public void testBasicExpectedBehavior() {
		Customer steve = doInHibernateSessionBuilder( this::jboss, session -> {
			Customer _steve = new Customer( 1L, "steve" );
			session.save( _steve );
			return _steve;
		} );

		doInHibernateSessionBuilder( this::acme, session -> {
			Customer check = session.get( Customer.class, steve.getId() );
			Assert.assertNull( "tenancy not properly isolated", check );
		} );

		doInHibernateSessionBuilder( this::jboss, session -> {
			session.delete( steve );
		} );
	}
