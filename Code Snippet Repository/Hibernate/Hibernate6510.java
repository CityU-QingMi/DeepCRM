	@Test
	public void testManyToOneInsideComponent() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Person p = new Person();
		Country bornIn = new Country();
		bornIn.setIso2( "FR" );
		bornIn.setName( "France" );
		p.bornIn = bornIn;
		p.name = "Emmanuel";
		AddressType type = new AddressType();
		type.setName( "Primary Home" );
		s.persist( type );
		Country currentCountry = new Country();
		currentCountry.setIso2( "US" );
		currentCountry.setName( "USA" );
		Address add = new Address();
		add.address1 = "4 square street";
		add.city = "San diego";
		add.country = currentCountry;
		add.type = type;
		p.address = add;
		s.persist( p );
		tx.commit();

		s = openSession();
		tx = s.beginTransaction();
		Query q = s.createQuery( "select p from Person p where p.address.city = :city" );
		q.setString( "city", add.city );
		List result = q.list();
		Person samePerson = (Person) result.get( 0 );
		assertNotNull( samePerson.address.type );
		assertEquals( type.getName(), samePerson.address.type.getName() );
		tx.commit();
		s.close();
	}
