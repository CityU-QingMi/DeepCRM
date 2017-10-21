	@Test
	public void testIdInEmbeddableSuperclass() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		FirTree chrismasTree = new FirTree();
		s.persist(chrismasTree);
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		chrismasTree = (FirTree) s.get(FirTree.class, chrismasTree.getId());
		assertNotNull(chrismasTree);
		s.delete(chrismasTree);
		tx.commit();
		s.close();
	}
