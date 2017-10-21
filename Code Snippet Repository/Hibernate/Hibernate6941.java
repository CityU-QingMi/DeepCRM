	@Test
	public void testPackageQueries() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Plane p = new Plane();
		s.persist( p );
		Query q = s.getNamedQuery( "plane.getAll" );
		assertEquals( 1, q.list().size() );
		s.delete( q.list().get( 0 ) );
		tx.commit();
		s.close();
	}
