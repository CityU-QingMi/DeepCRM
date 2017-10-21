	@Test
	public void testCMT() throws Exception {
		sessionFactory().getStatistics().clear();

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		Session s = openSession();
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
		assertFalse( s.isOpen() );

		assertEquals( sessionFactory().getStatistics().getFlushCount(), 0 );
		assertEquals( sessionFactory().getStatistics().getEntityInsertCount(), 0 );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		s = openSession();
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().rollback();
		assertFalse( s.isOpen() );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		s = openSession();
		Map item = new HashMap();
		item.put( "name", "The Item" );
		item.put( "description", "The only item we have" );
		s.persist( "Item", item );
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
		assertFalse( s.isOpen() );
		assertEquals( sessionFactory().getStatistics().getFlushCount(), 1 );
		assertEquals( sessionFactory().getStatistics().getEntityInsertCount(), 1 );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		s = openSession();
		item = ( Map ) s.createQuery( "from Item" ).uniqueResult();
		assertNotNull( item );
		s.delete( item );
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
		assertFalse( s.isOpen() );

		assertEquals( sessionFactory().getStatistics().getTransactionCount(), 4 );
		assertEquals( sessionFactory().getStatistics().getSuccessfulTransactionCount(), 3 );
		assertEquals( sessionFactory().getStatistics().getEntityDeleteCount(), 1 );
		assertEquals( sessionFactory().getStatistics().getEntityInsertCount(), 1 );
		assertEquals( sessionFactory().getStatistics().getSessionOpenCount(), 4 );
		assertEquals( sessionFactory().getStatistics().getSessionCloseCount(), 4 );
		assertEquals( sessionFactory().getStatistics().getQueryExecutionCount(), 1 );
		assertEquals( sessionFactory().getStatistics().getFlushCount(), 2 );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		s = openSession();
		s.createQuery( "delete from Item" ).executeUpdate();
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
	}
