	@Test
	public void testSubclassDeleteNaturalId() {
		Session s = openSession();
		s.beginTransaction();
		s.save( new User( "steve" ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Principal p = (Principal) s.bySimpleNaturalId( Principal.class ).load( "steve" );
		assertNotNull( p );

		s.delete( p );
		s.flush();

//		assertNull( s.bySimpleNaturalId( Principal.class ).load( "steve" ) );
		assertNull( s.bySimpleNaturalId( User.class ).load( "steve" ) );

		s.getTransaction().commit();
		s.close();
	}
