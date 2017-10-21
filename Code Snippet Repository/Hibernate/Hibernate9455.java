	@Test
	public void testManualSynchronization() {
		Session s = openSession();
		s.beginTransaction();

		sessionFactory().getStatistics().clear();

		// create an Organization...
		Organization jboss = new Organization( "JBoss" );
		s.persist( jboss );

		// now query on Employment, this should not cause an auto-flush
		s.createSQLQuery( getEmploymentSQL() ).addSynchronizedQuerySpace( "ABC" ).list();
		assertEquals( 0, sessionFactory().getStatistics().getEntityInsertCount() );

		// now try to query on Employment but this time add Organization as a synchronized query space...
		s.createSQLQuery( getEmploymentSQL() ).addSynchronizedEntityClass( Organization.class ).list();
		assertEquals( 1, sessionFactory().getStatistics().getEntityInsertCount() );

		// clean up
		s.delete( jboss );
		s.getTransaction().commit();
		s.close();
	}
