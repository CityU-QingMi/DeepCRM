	@Test
	@RequiresDialect( value = SybaseASE15Dialect.class )
	@FailureExpected( jiraKey = "" )
	public void testComponentQueryMethodNoParensFailureExpected() {
		// Sybase should translate "current_timestamp" in HQL to "getdate()";
		// This fails currently due to HHH-3510. The following test should be
		// deleted and testComponentQueries() should be updated (as noted
		// in that test case) when HHH-3510 is fixed.
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Employee emp = new Employee();
		emp.setHireDate( new Date() );
		emp.setPerson( new Person() );
		emp.getPerson().setName( "steve" );
		emp.getPerson().setDob( new Date() );
		s.save( emp );
		s.createQuery( "from Employee e where e.person = ('steve', current_timestamp)" ).list();
		s.delete( emp );
		t.commit();
		s.close();
	}
