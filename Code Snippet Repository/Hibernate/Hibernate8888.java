	@Test
	public void testNaturalIdChangedWhileDetached() {
		Session session = openSession();
		session.beginTransaction();
		Another it = new Another( "it" );
		session.save( it );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		it = session.bySimpleNaturalId( Another.class ).load( "it" );
		assertNotNull( it );
		session.getTransaction().commit();
		session.close();

		it.setName( "it2" );

		session = openSession();
		session.beginTransaction();
		session.update( it );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		it = session.bySimpleNaturalId( Another.class ).load( "it" );
		assertNull( it );
		it = session.bySimpleNaturalId( Another.class ).load( "it2" );
		assertNotNull( it );
		session.delete( it );
		session.getTransaction().commit();
		session.close();
	}
