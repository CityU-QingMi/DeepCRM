	@Test
	public void testComponentQueries() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Employee emp = new Employee();
		emp.setHireDate( new Date() );
		emp.setPerson( new Person() );
		emp.getPerson().setName( "steve" );
		emp.getPerson().setDob( new Date() );
		s.save( emp );

		s.createQuery( "from Employee e where e.person = :p and 1 = 1 and 2=2" ).setParameter( "p", emp.getPerson() ).list();
		s.createQuery( "from Employee e where :p = e.person" ).setParameter( "p", emp.getPerson() ).list();
		// The following fails on Sybase due to HHH-3510. When HHH-3510 
		// is fixed, the check for SybaseASE15Dialect should be removed.
		if ( ! ( getDialect() instanceof SybaseASE15Dialect ) ) {
			s.createQuery( "from Employee e where e.person = ('steve', current_timestamp)" ).list();
		}

		s.delete( emp );
		t.commit();
		s.close();
	}
