	@Test(expected = TransactionRequiredException.class)
	public void testInsertOutsideActiveTransaction() {
		Session session = openSession();

		// Illegal insertion of entity outside of active transaction.
		StrTestEntity entity = new StrTestEntity( "data" );
		session.persist( entity );
		session.flush();

		session.close();
	}
