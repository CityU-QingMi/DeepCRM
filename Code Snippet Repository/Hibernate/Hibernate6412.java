	@Test
	public void testLoadAndStore() {
		Session s = openSession();
		s.beginTransaction();
		Query q = new Query( new Location( "first", Location.Type.COUNTY ) );
		s.save( q );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		q = (Query) s.get( Query.class, q.getId() );
		assertEquals( 1, q.getIncludedLocations().size() );
		Location l = q.getIncludedLocations().iterator().next();
		assertEquals( Location.Type.COUNTY, l.getType() );
		s.delete( q );
		s.getTransaction().commit();
		s.close();
	}
