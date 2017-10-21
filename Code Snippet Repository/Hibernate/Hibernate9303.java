	@Test
	public void testScalarResult() {

		User user1 = new User( 1, "Chris" );
		User user2 = new User( 2, "Steve" );

		doInHibernate( this::sessionFactory, session -> {
			session.save( user1 );
			session.save( user2 );
		} );

		doInHibernate( this::sessionFactory, session -> {
			List<Object[]> users = session.createNativeQuery(
					"select * from users" ).getResultList();
			assertEquals( 2, users.size() );
		} );
	}
