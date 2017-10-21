	@Test
	public void testUpdateAnotherEntityWithNoSyncSpaces() {
		assertTrue( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );

		Session session = openSession();
		session.beginTransaction();
		session.createSQLQuery( "update Address set id = id" ).executeUpdate();
		session.getTransaction().commit();
		session.close();

		// NOTE false here because executeUpdate is different than selects
		assertFalse( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );
	}
