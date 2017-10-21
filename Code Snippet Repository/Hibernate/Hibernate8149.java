	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testScrollingJoinFetchesReverse() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction txn = s.beginTransaction();

		 ScrollableResults results = s
					.createQuery(
							"from Animal a left join fetch a.offspring where a.description like :desc order by a.id" )
					.setString( "desc", "root%" ).scroll();

		results.afterLast();

		int counter = 0;
		while ( results.previous() ) {
			counter++;
			Animal animal = ( Animal ) results.get( 0 );
			checkResult( animal );
		}
		assertEquals( "unexpected result count", 2, counter );

		txn.commit();
		s.close();

		data.cleanup();
	}
