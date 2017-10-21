	@Test
	public void testBatchingAmongstSubClasses() {
		doInHibernate( this::sessionFactory, session -> {
			int iterations = 12;
			for ( int i = 0; i < iterations; i++ ) {
				final Person person = new Person();
				person.addAddress( new Address() );
				session.persist( person );

				final SpecialPerson specialPerson = new SpecialPerson();
				specialPerson.addAddress( new Address() );
				session.persist( specialPerson );
			}
			connectionProvider.clear();
		} );

		assertEquals( 26, connectionProvider.getPreparedStatements().size() );
	}
