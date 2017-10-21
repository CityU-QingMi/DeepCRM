	@Test
	public void testManyToOneJoinTable() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		ForestType forest = new ForestType();
		forest.setName( "Original forest" );
		s.persist( forest );
		TreeType tree = new TreeType();
		tree.setForestType( forest );
		tree.setAlternativeForestType( forest );
		tree.setName( "just a tree");
		s.persist( tree );
		s.flush();
		s.clear();
		tree = (TreeType) s.get(TreeType.class, tree.getId() );
		assertNotNull( tree.getForestType() );
		assertNotNull( tree.getAlternativeForestType() );
		s.clear();
		forest = (ForestType) s.get( ForestType.class, forest.getId() );
		assertEquals( 1, forest.getTrees().size() );
		assertEquals( tree.getId(), forest.getTrees().iterator().next().getId() );
		tx.rollback();
		s.close();
	}
