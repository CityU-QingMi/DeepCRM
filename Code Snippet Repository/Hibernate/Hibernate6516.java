	@Test
	public void testBasicOps() {
		Session session = openSession();
		session.beginTransaction();
		Country country = new Country( "US", "United States of America" );
		session.persist( country );
		Person person = new Person( "Steve", new Address() );
		person.getAddress().setLine1( "123 Main" );
		person.getAddress().setCity( "Anywhere" );
		person.getAddress().setCountry( country );
		person.getAddress().setPostalCode( "123456789" );
		session.persist( person );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.createQuery( "from Person p where p.address.country.iso2 = 'US'" )
				.list();
		// same query!
		session.createQuery( "from Person p where p.address.country.id = 'US'" )
				.list();
		person = (Person) session.load( Person.class, person.getId() );
		session.delete( person );
		List countries = session.createQuery( "from Country" ).list();
		assertEquals( 1, countries.size() );
		session.delete( countries.get( 0 ) );

		session.getTransaction().commit();
		session.close();
	}
