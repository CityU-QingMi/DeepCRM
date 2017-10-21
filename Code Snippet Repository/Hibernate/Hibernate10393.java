	@Test(expected = TransactionRequiredException.class)
	public void testDeleteOutsideActiveTransaction() {
		Session session = openSession();

		// Revision 1
		session.getTransaction().begin();
		StrTestEntity entity = new StrTestEntity( "data" );
		session.persist( entity );
		session.getTransaction().commit();

		// Illegal removal of entity outside of active transaction.
		session.delete( entity );
		session.flush();

		session.close();
	}
