	@Test
	public void testResultSetMappingDefinition() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Organization ifa = new Organization("IFA");
		Organization jboss = new Organization("JBoss");
		Person gavin = new Person("Gavin");
		Employment emp = new Employment(gavin, jboss, "AU");

		s.persist(ifa);
		s.persist(jboss);
		s.persist(gavin);
		s.persist(emp);

		List l = s.createSQLQuery( getOrgEmpRegionSQL() )
				.setResultSetMapping( "org-emp-regionCode" )
				.list();
		assertEquals( l.size(), 2 );

		l = s.createSQLQuery( getOrgEmpPersonSQL() )
				.setResultSetMapping( "org-emp-person" )
				.list();
		assertEquals( l.size(), 1 );

		s.delete(emp);
		s.delete(gavin);
		s.delete(ifa);
		s.delete(jboss);

		t.commit();
		s.close();
	}
