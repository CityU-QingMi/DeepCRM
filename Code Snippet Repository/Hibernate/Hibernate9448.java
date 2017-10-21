	@Test
	public void testEntityStoredProcedure() throws HibernateException, SQLException {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Organization ifa = new Organization( "IFA" );
		Organization jboss = new Organization( "JBoss" );
		Person gavin = new Person( "Gavin" );
		Employment emp = new Employment( gavin, jboss, "AU" );
		s.persist( ifa );
		s.persist( jboss );
		s.persist( gavin );
		s.persist( emp );

		Query namedQuery = s.getNamedQuery( "selectAllEmployments" );
		List list = namedQuery.list();
		assertTrue( list.get( 0 ) instanceof Employment );
		s.delete( emp );
		s.delete( ifa );
		s.delete( jboss );
		s.delete( gavin );

		t.commit();
		s.close();
	}
