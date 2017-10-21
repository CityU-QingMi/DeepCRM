	@Test
	public void testOnlyCustomStrategyConsultedOnNonDirty() throws Exception {
		Session session = openSession();
		session.beginTransaction();
		Long id = (Long) session.save( new Thing( INITIAL_NAME ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		Thing thing = (Thing) session.get( Thing.class, id );
		// lets change the name
		thing.setName( SUBSEQUENT_NAME );
		assertTrue( Strategy.INSTANCE.isDirty( thing, null, null ) );
		// but fool the dirty map
		thing.changedValues.clear();
		assertFalse( Strategy.INSTANCE.isDirty( thing, null, null ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		thing = (Thing) session.get( Thing.class, id );
		assertEquals( INITIAL_NAME, thing.getName() );
		session.createQuery( "delete Thing" ).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
