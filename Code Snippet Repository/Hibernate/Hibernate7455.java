	@Test
	public void dirtyCheckAgainstNewNameInstance() {
		SomeEntity simpleEntity = new SomeEntity();
		simpleEntity.setId( 1L );
		simpleEntity.setName( new Name( "Steven" ) );

		Session session = openSession();
		session.getTransaction().begin();
		session.save( simpleEntity );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		SomeEntity loaded = session.byId( SomeEntity.class ).load( 1L );
		loaded.setName( new Name( "Steve" ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		loaded = session.byId( SomeEntity.class ).load( 1L );
		assertEquals( "Steve", loaded.getName().getText() );
		session.delete( loaded );
		session.getTransaction().commit();
		session.close();
	}
