	@Test
	public void testMultiPathGetAndModify() throws Exception {
		// persist a simple A in the database

		Session s = openSession();
		s.beginTransaction();
		A a = new A();
		a.setData( "Anna" );
		s.save( a );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		// retrieve the previously saved instance from the database, and update it
		a = (A) s.get( A.class, new Long( a.getId() ) );
		modifyEntity( a );
		s.getTransaction().commit();
		s.close();

		verifyModifications( a.getId() );
	}
