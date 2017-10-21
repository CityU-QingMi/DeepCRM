	@Test
	public void testBatchingAmongstSubClasses() {
		doInHibernate( this::sessionFactory, session -> {
			int iterations = 2;
			for ( int i = 0; i < iterations; i++ ) {
				final President president = new President();
				president.addAddress( new Address() );
				session.persist( president );

				final AnotherPerson anotherPerson = new AnotherPerson();
				Office office = new Office();
				session.persist( office );
				anotherPerson.office = office;
				session.persist( anotherPerson );

				final Person person = new Person();
				session.persist( person );

				final SpecialPerson specialPerson = new SpecialPerson();
				specialPerson.addAddress( new Address() );
				session.persist( specialPerson );
			}
			connectionProvider.clear();
		} );

		assertEquals( 17, connectionProvider.getPreparedStatements().size() );
	}
