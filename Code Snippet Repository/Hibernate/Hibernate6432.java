	public void testCase() {
		Session s = openSession();
		s.getTransaction().begin();

		Person p = new Person();
		p.setName( "Alfio" );
		PersonInfo pi = new PersonInfo();
		pi.setId( p );
		pi.setInfo( "Some information" );
		s.persist( p );
		s.persist( pi );

		s.getTransaction().commit();
		s.clear();

		s.getTransaction().begin();

		Query q = s.getNamedQuery( "PersonQuery" );
		List<Person> persons = q.list();
		assertEquals( persons.size(), 1 );
		assertEquals( persons.get( 0 ).getName(), "Alfio" );

		s.getTransaction().commit();
		s.clear();

		s.getTransaction().begin();

		p = (Person) s.get( Person.class, persons.get( 0 ).getId() );
		assertEquals( p.getName(), "Alfio" );

		s.getTransaction().commit();
		s.close();
	}
