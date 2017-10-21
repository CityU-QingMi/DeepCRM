	public void testPaginationWithPolymorphicQuery() {
		Session s = openSession();
		s.beginTransaction();
		Human h = new Human();
		h.setName( new Name( "Steve", null, "Ebersole" ) );
		s.save( h );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List results = s.createQuery( "from java.lang.Object" ).setMaxResults( 2 ).list();
		assertEquals( 1, results.size() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( h );
		s.getTransaction().commit();
		s.close();
	}
