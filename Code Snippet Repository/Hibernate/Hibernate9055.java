	@Test
	public void testJoinFetchPropertyRef() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Person p = new Person();
		p.setName("Steve");
		p.setUserId("steve");
		Address a = new Address();
		a.setAddress("Texas");
		a.setCountry("USA");
		p.setAddress(a);
		a.setPerson(p);
		s.save(p);

		s.flush();
		s.clear();

		sessionFactory().getStatistics().clear();

		p = (Person) s.get( Person.class, p.getId() ); //get address reference by outer join
		
		assertTrue( Hibernate.isInitialized( p.getAddress() ) );
		assertNotNull( p.getAddress() );
        assertEquals( sessionFactory().getStatistics().getPrepareStatementCount(), 1 );
        assertEquals( sessionFactory().getStatistics().getEntityFetchCount(), 0 );

		s.clear();

		sessionFactory().getStatistics().clear();

		p = (Person) s.createCriteria(Person.class)
			.setFetchMode("address", FetchMode.SELECT)
			.uniqueResult(); //get address reference by select
		
		assertTrue( Hibernate.isInitialized( p.getAddress() ) );
		assertNotNull( p.getAddress() );
        assertEquals( sessionFactory().getStatistics().getPrepareStatementCount(), 2 );
        assertEquals( sessionFactory().getStatistics().getEntityFetchCount(), 0 );

		s.createQuery("delete from Address").executeUpdate();
		s.createQuery("delete from Person").executeUpdate();
		
		t.commit();
		s.close();
	}
