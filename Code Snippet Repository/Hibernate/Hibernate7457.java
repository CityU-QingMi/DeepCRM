	@Test
	public void dirtyCheckAgainstNewNumberInstance() {
		// numbers (and most other java types) are actually immutable...
		SomeEntity simpleEntity = new SomeEntity();
		simpleEntity.setId( 1L );
		simpleEntity.setNumber( 1 );

		Session session = openSession();
		session.getTransaction().begin();
		session.save( simpleEntity );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		SomeEntity loaded = session.byId( SomeEntity.class ).load( 1L );
		loaded.setNumber( 2 );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();
		loaded = session.byId( SomeEntity.class ).load( 1L );
		assertEquals( 2, loaded.getNumber().intValue() );
		session.delete( loaded );
		session.getTransaction().commit();
		session.close();
	}
