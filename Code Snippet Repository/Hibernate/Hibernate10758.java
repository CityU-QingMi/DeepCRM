	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		Session session = openSession();
		session.getTransaction().begin();
		GivenIdStrEntity entity = new GivenIdStrEntity( 1, "data" );
		session.persist( entity );
		session.getTransaction().commit();

		// Revision 2
		session.getTransaction().begin();
		session.persist( new StrTestEntity( "another data" ) ); // Just to create second revision.
		entity = (GivenIdStrEntity) session.get( GivenIdStrEntity.class, 1 );
		session.delete( entity ); // First try to remove the entity.
		session.save( entity ); // Then save it.
		session.getTransaction().commit();

		// Revision 3
		session.getTransaction().begin();
		entity = (GivenIdStrEntity) session.get( GivenIdStrEntity.class, 1 );
		session.delete( entity ); // First try to remove the entity.
		entity.setData( "modified data" ); // Then change it's state.
		session.save( entity ); // Finally save it.
		session.getTransaction().commit();

		session.close();
	}
