	@Test
	public void testNonLazyBagKeyPropertyRef() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Person p = new Person();
		p.setName( "Steve" );
		p.setUserId( "steve" );
		p.getSystems().add( "QA" );
		p.getSystems().add( "R&D" );
		s.persist( p );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.createQuery( "from Person" ).list();
		s.clear();
		s.createSQLQuery( "select {p.*} from PROPREF_PERS {p}" )
				.addEntity( "p", Person.class.getName() )
				.list();
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		List results = s.createQuery( "from Person" ).list();
		Iterator itr = results.iterator();
		while ( itr.hasNext() ) {
			s.delete( itr.next() );
		}
		t.commit();
		s.close();
	}
