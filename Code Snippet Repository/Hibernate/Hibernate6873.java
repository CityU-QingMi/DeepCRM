	@Test
	@TestForIssue(jiraKey = "")
	public void testOrderByNoElement() {

		final Session s = openSession();
		s.getTransaction().begin();

		Employee employee = new Employee( 1 );

		Computer computer = new Computer( 1 );
		computer.setComputerName( "Bob's computer" );
		computer.setEmployee( employee );

		Computer computer2 = new Computer( 2 );
		computer2.setComputerName( "Alice's computer" );
		computer2.setEmployee( employee );

		s.save( employee );
		s.save( computer2 );
		s.save( computer );

		s.flush();
		s.clear();
		sessionFactory().getCache().evictEntityRegions();

		employee = (Employee) s.get( Employee.class, employee.getId() );

		assertEquals( 2, employee.getAssets().size() );
		assertEquals( 1, employee.getAssets().get( 0 ).getIdAsset().intValue() );
		assertEquals( 2, employee.getAssets().get( 1 ).getIdAsset().intValue() );
	}
