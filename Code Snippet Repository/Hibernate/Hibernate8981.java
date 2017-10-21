	@Test
	public void testBasicMultiLoadWithManagedAndChecking() {
		Session session = openSession();
		session.getTransaction().begin();
		SimpleEntity first = session.byId( SimpleEntity.class ).load( 1 );
		List<SimpleEntity> list = session.byMultipleIds( SimpleEntity.class ).enableSessionCheck( true ).multiLoad( ids(56) );
		assertEquals( 56, list.size() );
		// this check is HIGHLY specific to implementation in the batch loader
		// which puts existing managed entities first...
		assertSame( first, list.get( 0 ) );
		session.getTransaction().commit();
		session.close();
	}
