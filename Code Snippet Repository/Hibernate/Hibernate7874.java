	@Test
	public void testSequenceIdentityGenerator() {
		Session session = openSession();
		session.beginTransaction();

		MyEntity e = new MyEntity( "entity-1" );
		session.save( e );

		// this insert should happen immediately!
		assertNotNull( "id not generated through forced insertion", e.getId() );

		session.delete( e );
		session.getTransaction().commit();
		session.close();
	}
