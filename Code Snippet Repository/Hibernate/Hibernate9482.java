	@Test
	public void testSelectAnotherEntityWithNoSyncSpaces() {
		assertTrue( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );

		Session session = openSession();
		session.createSQLQuery( "select * from Address" ).list();
		session.close();

		assertTrue( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );
	}
