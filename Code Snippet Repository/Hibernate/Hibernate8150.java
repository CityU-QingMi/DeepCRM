	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testScrollingJoinFetchesPositioning() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction txn = s.beginTransaction();

 		ScrollableResults results = s
		        .createQuery( "from Animal a left join fetch a.offspring where a.description like :desc order by a.id" )
		        .setString( "desc", "root%" )
		        .scroll();

		results.first();
		Animal animal = ( Animal ) results.get( 0 );
		assertEquals( "first() did not return expected row", data.root1Id, animal.getId() );

		results.scroll( 1 );
		animal = ( Animal ) results.get( 0 );
		assertEquals( "scroll(1) did not return expected row", data.root2Id, animal.getId() );

		results.scroll( -1 );
		animal = ( Animal ) results.get( 0 );
		assertEquals( "scroll(-1) did not return expected row", data.root1Id, animal.getId() );

		results.setRowNumber( 1 );
		animal = ( Animal ) results.get( 0 );
		assertEquals( "setRowNumber(1) did not return expected row", data.root1Id, animal.getId() );

		results.setRowNumber( 2 );
		animal = ( Animal ) results.get( 0 );
		assertEquals( "setRowNumber(2) did not return expected row", data.root2Id, animal.getId() );

		txn.commit();
		s.close();

		data.cleanup();
	}
