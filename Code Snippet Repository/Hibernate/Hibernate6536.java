	@Test
	public void testNonLazy() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Forest f = new Forest();
		Tree t = new Tree();
		t.setName( "Basic one" );
		s.persist( f );
		s.persist( t );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		f = (Forest) s.load( Forest.class, f.getId() );
		t = (Tree) s.load( Tree.class, t.getId() );
		assertFalse( "Default should be lazy", Hibernate.isInitialized( f ) );
		assertTrue( "Tree is not lazy", Hibernate.isInitialized( t ) );
		tx.commit();
		s.close();
	}
