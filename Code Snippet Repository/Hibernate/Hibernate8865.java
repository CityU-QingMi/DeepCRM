	@Test
	public void testIt() {
		Session s = openSession();
		s.beginTransaction();
		s.save(  new User( "steve" ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.bySimpleNaturalId( Principal.class ).load( "steve" );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.bySimpleNaturalId( User.class ).load( "steve" );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( s.bySimpleNaturalId( User.class ).load( "steve" ) );
		s.getTransaction().commit();
		s.close();
	}
