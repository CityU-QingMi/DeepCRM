	@Test
	public void testMergeManaged() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		NumberedNode root = new NumberedNode("root");
		s.persist(root);
		tx.commit();

		clearCounts();

		tx = s.beginTransaction();
		NumberedNode child = new NumberedNode("child");
		root.addChild( child );
		assertSame( root, s.merge( root ) );
		Object mergedChild = root.getChildren().iterator().next();
		assertNotSame( mergedChild, child );
		assertTrue( s.contains( mergedChild ) );
		assertFalse( s.contains(child) );
		assertEquals( root.getChildren().size(), 1 );
		assertTrue( root.getChildren().contains(mergedChild) );
		//assertNotSame( mergedChild, s.merge(child) ); //yucky :(
		tx.commit();

		assertInsertCount( 1 );
		assertUpdateCount( 0 );

		assertEquals( root.getChildren().size(), 1 );
		assertTrue( root.getChildren().contains(mergedChild) );

		s.beginTransaction();
		assertEquals(
				Long.valueOf( 2 ),
				s.createCriteria( NumberedNode.class )
						.setProjection( Projections.rowCount() )
						.uniqueResult()
		);
		s.getTransaction().commit();
		s.close();

		cleanup();
	}
