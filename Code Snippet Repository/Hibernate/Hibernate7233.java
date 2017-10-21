	@Test
	public void testMultiPathUpdateModifiedDetached() throws Exception {
		// persist a simple A in the database

		Session s = openSession();
		s.beginTransaction();
		A a = new A();
		a.setData( "Anna" );
		s.save( a );
		s.getTransaction().commit();
		s.close();

		// modify detached entity
		modifyEntity( a );

		s = openSession();
		s.beginTransaction();
		s.update( a );
		s.getTransaction().commit();
		s.close();

		verifyModifications( a.getId() );
	}
