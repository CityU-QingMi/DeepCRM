	@Test
	public void testOrderByEmployee() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Employer employer = new Employer();
		Employee employee1 = new Employee();
		employee1.setName( "Emmanuel" );
		Employee employee2 = new Employee();
		employee2.setName( "Alice" );
		s.persist( employee1 );
		s.persist( employee2 );
		Set erColl = new HashSet();
		Collection eeColl = new ArrayList();
		Collection eeColl2 = new ArrayList();
		erColl.add( employee1 );
		erColl.add( employee2 );
		eeColl.add( employer );
		eeColl2.add( employer );
		employer.setEmployees( erColl );
		employee1.setEmployers( eeColl );
		employee2.setEmployers( eeColl2 );

		s.flush();
		s.clear();

		employer = (Employer) s.get( Employer.class, employer.getId() );
		assertNotNull( employer );
		assertNotNull( employer.getEmployees() );
		assertEquals( 2, employer.getEmployees().size() );
		Employee eeFromDb = (Employee) employer.getEmployees().iterator().next();
		assertEquals( employee2.getName(), eeFromDb.getName() );
		tx.rollback();
		s.close();
	}
