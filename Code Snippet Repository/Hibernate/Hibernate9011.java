	@Test
	@TestForIssue(jiraKey = "")
	public void testReplacedWhileManaged() {
		createData();

		Session session = openSession();
		session.beginTransaction();
		List results = session.createQuery( "from EmployeeInfo" ).list();
		assertEquals( 1, results.size() );
		results = session.createQuery( "from Employee" ).list();
		assertEquals( 1, results.size() );
		Employee emp = (Employee) results.get( 0 );
		assertNotNull( emp.getInfo() );

		// Replace with a new EmployeeInfo instance
		emp.setInfo( new EmployeeInfo() );

		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		emp = (Employee) session.get( Employee.class, emp.getId() );
		assertNotNull( emp.getInfo() );
		results = session.createQuery( "from EmployeeInfo" ).list();
		assertEquals( 1, results.size() );
		results = session.createQuery( "from Employee" ).list();
		assertEquals( 1, results.size() );
		session.getTransaction().commit();
		session.close();

		cleanupData();
	}
