	@Test
	public void testOneToOneOnSubclass() {
		Map person = new HashMap();
		person.put( "name", "Steve" );
		Map address = new HashMap();
		address.put( "zip", "12345" );
		address.put( "state", "TX" );
		address.put( "street", "123 Main St" );

		person.put( "address", address );
		address.put( "owner", person );

		Session s = openSession();
		s.beginTransaction();
		s.persist( "Person", person );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		EntityStatistics addressStats = sessionFactory().getStatistics().getEntityStatistics( "Address" );

		person = ( Map ) s.createQuery( "from Person p join fetch p.address" ).uniqueResult();
		assertNotNull( "could not locate person", person );
		assertNotNull( "could not locate persons address", person.get( "address" ) );
		s.clear();

		Object[] tuple = ( Object[] ) s.createQuery( "select p.name, p from Person p join fetch p.address" ).uniqueResult();
		assertEquals( tuple.length, 2 );
		person = ( Map ) tuple[1];
		assertNotNull( "could not locate person", person );
		assertNotNull( "could not locate persons address", person.get( "address" ) );

		s.delete( "Person", person );

		s.getTransaction().commit();
		s.close();

		assertEquals( addressStats.getFetchCount(), 0 );
	}
