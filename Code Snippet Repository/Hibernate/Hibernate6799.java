	@Test
	public void testOneToOneJoinTable() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		ForestType forest = new ForestType();
		forest.setName( "Original forest" );
		s.persist( forest );
		BiggestForest forestRepr = new BiggestForest();
		forestRepr.setType( forest );
		forest.setBiggestRepresentative( forestRepr );
		s.persist( forestRepr );
		s.flush();
		s.clear();
		forest = (ForestType) s.get( ForestType.class, forest.getId() );
		assertNotNull( forest.getBiggestRepresentative() );
		assertEquals( forest, forest.getBiggestRepresentative().getType() );
		tx.rollback();
		s.close();
	}
