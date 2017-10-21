	@Test
	public void testInstantUsageAsVersion() {
		Session session = openSession();
		session.getTransaction().begin();
		TheEntity e = new TheEntity( 1 );
		session.save( e );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		e = session.byId( TheEntity.class ).load( 1 );
		assertThat( e.getTs(), notNullValue() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		e = session.byId( TheEntity.class ).load( 1 );
		session.delete( e );
		session.getTransaction().commit();
		session.close();
	}
