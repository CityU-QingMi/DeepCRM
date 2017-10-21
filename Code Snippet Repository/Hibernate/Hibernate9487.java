	@Test
	public void testUpdateCachedEntityWithSyncSpaces() {
		assertTrue( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );

		Session session = openSession();
		session.beginTransaction();
		session.createSQLQuery( "update Customer set id = id" ).addSynchronizedEntityClass( Customer.class ).executeUpdate();
		session.getTransaction().commit();
		session.close();

		assertFalse( sessionFactory().getCache().containsEntity( Customer.class, 1 ) );
	}
