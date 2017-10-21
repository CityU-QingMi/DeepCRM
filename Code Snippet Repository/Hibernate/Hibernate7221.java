	@Test
	public void test() {
		// create a StateCodes
		Session s = openSession();
		s.beginTransaction();
		s.save( new StateCodes( 1 ) );
		s.getTransaction().commit();
		s.close();

		// now try to load a ZipCodes using the same id : should just return null rather than blow up :)
		s = openSession();
		s.beginTransaction();
		ZipCodes zc = s.find( ZipCodes.class, 1 );
		assertNull( zc );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.find( ZipCodes.class, 1 );
		s.getTransaction().commit();
		s.close();
	}
