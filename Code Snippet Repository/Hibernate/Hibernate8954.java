	@Test
	public void testMergeManagedInitializedCollection() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		NumberedNode root = new NumberedNode( "root" );
		root.addChild( new NumberedNode( "child" ) );
		s.persist(root);
		tx.commit();
		s.close();

		clearCounts();

		NumberedNode newRoot = new NumberedNode( "root" );
		newRoot.setId( root.getId() );

		s = openSession();
		tx = s.beginTransaction();
		root = ( NumberedNode ) s.get( NumberedNode.class, root.getId() );
		Set managedChildren = root.getChildren();
		Hibernate.initialize( managedChildren );
		assertTrue( Hibernate.isInitialized( managedChildren ) );
		newRoot.setChildren( managedChildren );
		assertSame( root, s.merge( newRoot ) );
		assertSame( managedChildren, root.getChildren() );
		assertTrue( Hibernate.isInitialized( managedChildren ) );
		tx.commit();

		assertInsertCount(0);
		assertUpdateCount(0);
		assertDeleteCount(0);

		tx = s.beginTransaction();
		assertEquals(
				Long.valueOf( 2 ),
				s.createCriteria(NumberedNode.class)
						.setProjection( Projections.rowCount() )
						.uniqueResult()
		);
		tx.commit();

		s.close();

		cleanup();
	}
