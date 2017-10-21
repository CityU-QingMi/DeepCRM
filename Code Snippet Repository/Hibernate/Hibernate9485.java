	@Test
	public void testSelectCachedEntityWithNoSyncSpaces() {
		assertTrue( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );

		Session session = openSession();
		session.createSQLQuery( "select * from Customer" ).list();
		session.close();

		assertTrue( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );
	}
