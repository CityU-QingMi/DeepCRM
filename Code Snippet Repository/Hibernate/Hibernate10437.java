	@Test
	public void testRevisionHistory() {
		final Simple rev1 = getAuditReader().find( Simple.class, 1, 1 );
		assertEquals( 2, rev1.getEmbeddedMap().entrySet().size() );
		TestTools.assertCollectionsEqual(
				TestTools.<String, String>mapBuilder()
						.add( "1", "One" )
						.add( "2", "Two" )
						.entries(),
				rev1.getEmbeddedMap().entrySet()
		);

		final Simple rev2 = getAuditReader().find( Simple.class, 1, 2 );
		assertEquals( 3, rev2.getEmbeddedMap().entrySet().size() );
		TestTools.assertCollectionsEqual(
				TestTools.<String,String>mapBuilder()
						.add( "1", "One" )
						.add( "2", "Two" )
						.add( "3", "Three" )
						.entries(),
				rev2.getEmbeddedMap().entrySet()
		);

		final Simple rev3 = getAuditReader().find( Simple.class, 1, 3 );
		assertEquals( 1, rev3.getEmbeddedMap().entrySet().size() );
		TestTools.assertCollectionsEqual(
				TestTools.<String,String>mapBuilder()
						.add( "3", "Three" )
						.entries(),
				rev3.getEmbeddedMap().entrySet()
		);

		final Simple rev4 = getAuditReader().find( Simple.class, 1, 4 );
		TestTools.assertCollectionsEqual(
				TestTools.<String,String>mapBuilder()
						.add( "3", "Three-New" )
						.entries(),
				rev4.getEmbeddedMap().entrySet()
		);

		final Simple rev5 = getAuditReader().find( Simple.class, 1, 5 );
		assertEquals( 0, rev5.getEmbeddedMap().size() );
	}
