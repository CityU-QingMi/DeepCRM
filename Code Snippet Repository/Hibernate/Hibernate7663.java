	@Test
	public void testOnlyCustomStrategy() {
		Session session = openSession();
		session.beginTransaction();
		Long id = (Long) session.save( new Thing( INITIAL_NAME ) );
		session.getTransaction().commit();
		session.close();

		Strategy.INSTANCE.resetState();

		session = openSession();
		session.beginTransaction();
		Thing thing = (Thing) session.get( Thing.class, id );
		thing.setName( SUBSEQUENT_NAME );
		session.getTransaction().commit();
		session.close();

		assertEquals( 1, Strategy.INSTANCE.canDirtyCheckCount );
		assertEquals( 1, Strategy.INSTANCE.isDirtyCount );
		assertEquals( 1, Strategy.INSTANCE.resetDirtyCount );
		assertEquals( 1, Strategy.INSTANCE.findDirtyCount );

		session = openSession();
		session.beginTransaction();
		thing = (Thing) session.get( Thing.class, id );
		assertEquals( SUBSEQUENT_NAME, thing.getName() );
		session.delete( thing );
		session.getTransaction().commit();
		session.close();
	}
