	@Test(expected = TransactionRequiredException.class)
	public void testUpdateOutsideActiveTransaction() {
		Session session = openSession();

		// Revision 1
		session.getTransaction().begin();
		StrTestEntity entity = new StrTestEntity( "data" );
		session.persist( entity );
		session.getTransaction().commit();

		// Illegal modification of entity state outside of active transaction.
		entity.setStr( "modified data" );
		session.update( entity );
		session.flush();

		session.close();
	}
