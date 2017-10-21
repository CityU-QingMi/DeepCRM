	@Test
	public void testSaveOrUpdateManaged() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		NumberedNode root = new NumberedNode( "root" );
		s.saveOrUpdate( root );
		tx.commit();

		tx = s.beginTransaction();
		NumberedNode child = new NumberedNode( "child" );
		root.addChild( child );
		s.saveOrUpdate( root );
		assertFalse( s.contains( child ) );
		s.flush();
		assertTrue( s.contains( child ) );
		tx.commit();

		assertTrue( root.getChildren().contains( child ) );
		assertEquals( root.getChildren().size(), 1 );

		tx = s.beginTransaction();
		assertEquals(
				Long.valueOf( 2 ),
				s.createCriteria( NumberedNode.class )
						.setProjection( Projections.rowCount() )
						.uniqueResult()
		);
		s.delete( root );
		s.delete( child );
		tx.commit();
		s.close();
	}
