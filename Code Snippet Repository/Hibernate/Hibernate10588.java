	@Test
	public void testCollectionHistory() {
		final ComponentEntity rev1 = getAuditReader().find( ComponentEntity.class, id, 1 );
		assertEquals( 2, rev1.getComponents().size() );
		assertEquals(
				TestTools.makeSet(
						new Component( "User1", "Test1" ),
						new Component( "User2", "Test2" )
				),
				rev1.getComponents()
		);

		final ComponentEntity rev2 = getAuditReader().find( ComponentEntity.class, id, 2 );
		assertEquals( 2, rev2.getComponents().size() );
		assertEquals(
				TestTools.makeSet(
						new Component( "User1-Inline", "Test1" ),
						new Component( "User2", "Test2" )
				),
				rev2.getComponents()
		);

		final ComponentEntity rev3 = getAuditReader().find( ComponentEntity.class, id, 3 );
		assertEquals( 2, rev3.getComponents().size() );
		assertEquals(
				TestTools.makeSet(
						new Component( "User1-Inline", "Test1" ),
						new Component( "User2", "Test3" )
				),
				rev3.getComponents()
		);
	}
