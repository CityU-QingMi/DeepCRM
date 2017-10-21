	@Test
	@TestForIssue( jiraKey = "" )
	public void testOrphanedWhileDetached() {
		createData();

		Session session = openSession();
		session.beginTransaction();
		List results = session.createQuery( "from EmployeeInfo" ).list();
		assertEquals( 1, results.size() );
		results = session.createQuery( "from Employee" ).list();
		assertEquals( 1, results.size() );
		Employee emp = ( Employee ) results.get( 0 );
		assertNotNull( emp.getInfo() );

		//only fails if the object is detached
		session.getTransaction().commit();
		session.close();
		session = openSession();
		session.beginTransaction();

		emp.setInfo( null );

		//save using the new session (this used to work prior to 3.5.x)
		session.saveOrUpdate(emp);

		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		emp = ( Employee ) session.get( Employee.class, emp.getId() );
		assertNull( emp.getInfo() );
		// TODO: If merge was used instead of saveOrUpdate, this would work.
		// However, re-attachment does not currently support handling orphans.
		// See HHH-3795
//		results = session.createQuery( "from EmployeeInfo" ).list();
//		assertEquals( 0, results.size() );
		results = session.createQuery( "from Employee" ).list();
		assertEquals( 1, results.size() );
		session.getTransaction().commit();
		session.close();

		cleanupData();
	}
