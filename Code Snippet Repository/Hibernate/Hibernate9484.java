	@Test
	public void testUpdateAnotherEntityWithSyncSpaces() {
		assertTrue( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );

		Session session = openSession();
		session.beginTransaction();
		session.createSQLQuery( "update Address set id = id" ).addSynchronizedEntityClass( Address.class ).executeUpdate();
		session.getTransaction().commit();
		session.close();

		assertTrue( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );
	}
