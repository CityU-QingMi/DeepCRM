	@Test
	public void testTransactionTimeoutSuccess() {
		Session session = openSession();
		Transaction transaction = session.getTransaction();
		transaction.setTimeout( 60 );
		transaction.begin();
		session.persist( new Person( "Lukasz", "Antoniak" ) );
		transaction.commit();
		session.close();
	}
