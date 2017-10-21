	@Test
	@TestForIssue( jiraKey = "" )
	public void testNormalUsage() {
		Session session = openSession();
		session.beginTransaction();
		session.save( new IsolatedEvictableEntity( 1 ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		IsolatedEvictableEntity entity = (IsolatedEvictableEntity) session.get( IsolatedEvictableEntity.class, 1 );
		assertTrue( session.contains( entity ) );
		session.evict( entity );
		assertFalse( session.contains( entity ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.delete( entity );
		session.getTransaction().commit();
		session.close();
	}
