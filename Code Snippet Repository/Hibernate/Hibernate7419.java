	@Test
	public void testConnectionMaintanenceDuringFlush() throws Throwable {
		final Statistics statistics = sessionFactory().getStatistics();
		prepare();
		Session s = getSessionUnderTest();

		List<Silly> entities = new ArrayList<Silly>();
		for ( int i = 0; i < 10; i++ ) {
			Other other = new Other( "other-" + i );
			Silly silly = new Silly( "silly-" + i, other );
			entities.add( silly );
			s.save( silly );
		}
		s.flush();

		for ( Silly silly : entities ) {
			silly.setName( "new-" + silly.getName() );
			silly.getOther().setName( "new-" + silly.getOther().getName() );
		}
		long initialCount = statistics.getConnectCount();
		s.flush();
		assertEquals( "connection not maintained through flush", initialCount + 1, statistics.getConnectCount() );

		s.createQuery( "delete from Silly" ).executeUpdate();
		s.createQuery( "delete from Other" ).executeUpdate();
		s.getTransaction().commit();
		release( s );
		done();
	}
