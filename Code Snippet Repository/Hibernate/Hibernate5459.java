	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "") 
	public void testPersistBeforeTransaction() {
		Session session = openSession();
		RootEntity ent1_0 = new RootEntity();
		RootEntity ent1_1 = new RootEntity();

		session.persist( ent1_0 );
		session.persist( ent1_1 );

		Transaction tx = session.beginTransaction();
		tx.commit(); // flush
	}
