	@Test
	public void testBatchOrdering() {
		doInHibernate( this::sessionFactory, session -> {
			final Person person = new Person();
			person.addAddress( new Address() );
			session.persist( person );

			// Derived Object with dependent object (address)
			final SpecialPerson specialPerson = new SpecialPerson();
			specialPerson.addAddress( new Address() );
			session.persist( specialPerson );
		} );
	}
