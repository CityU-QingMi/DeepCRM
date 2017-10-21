	@Test
	public void testOneToManyFetchEager() throws Exception {
		Branch b = new Branch();
		Session s = openSession( );
		s.getTransaction().begin();
		s.persist( b );
		s.flush();
		Leaf l = new Leaf();
		l.setBranch( b );
		s.persist( l );
		s.flush();

		s.clear();

		s.createCriteria( Branch.class ).list();

		s.getTransaction().rollback();
		s.close();
	}
