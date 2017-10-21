	@Test
	public void testMultiLoadClearsBatchFetchQueue() {
		final EntityKey entityKey = new EntityKey(
				1,
				sessionFactory().getEntityPersister( SimpleEntity.class.getName() )
		);

		Session session = openSession();
		session.getTransaction().begin();
		// create a proxy, which should add an entry to the BatchFetchQueue
		SimpleEntity first = session.byId( SimpleEntity.class ).getReference( 1 );
		assertTrue( ( (SessionImplementor) session ).getPersistenceContext().getBatchFetchQueue().containsEntityKey( entityKey ) );

		// now bulk load, which should clean up the BatchFetchQueue entry
		List<SimpleEntity> list = session.byMultipleIds( SimpleEntity.class ).enableSessionCheck( true ).multiLoad( ids(56) );

		assertEquals( 56, list.size() );
		assertFalse( ( (SessionImplementor) session ).getPersistenceContext().getBatchFetchQueue().containsEntityKey( entityKey ) );

		session.getTransaction().commit();
		session.close();
	}
