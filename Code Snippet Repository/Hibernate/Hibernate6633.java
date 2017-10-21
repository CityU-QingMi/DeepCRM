	@Test
	public void testGenericGenerators() throws Exception {
		// Ensures that GenericGenerator annotations wrapped inside a GenericGenerators holder are bound correctly
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Monkey monkey = new Monkey();
		s.persist( monkey );
		s.flush();
		assertNotNull( monkey.getId() );
		tx.rollback();
		s.close();
	}
