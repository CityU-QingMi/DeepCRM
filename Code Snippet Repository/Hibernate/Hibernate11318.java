	@Test
	@Priority(10)
	public void testSchemaCreation() {
		// Populate database with test data.
		Session session = getSession();
		session.getTransaction().begin();
		StrTestEntity entity = new StrTestEntity( "data" );
		session.save( entity );
		session.getTransaction().commit();

		id = entity.getId();
	}
