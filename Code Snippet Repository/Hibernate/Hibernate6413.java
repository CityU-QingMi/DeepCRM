	@Test
	@TestForIssue(jiraKey = "")
	public void testEmbeddableWithNullables() {
		Session s = openSession();
		s.beginTransaction();
		Query q = new Query( new Location( null, Location.Type.COMMUNE ) );
		s.save( q );
		s.getTransaction().commit();
		s.clear();
		
		s.beginTransaction();
		q.getIncludedLocations().add( new Location( null, Location.Type.COUNTY ) );
		s.update( q );
		s.getTransaction().commit();
		s.clear();
		
		s.beginTransaction();
		q = (Query) s.get( Query.class, q.getId() );
//		assertEquals( 2, q.getIncludedLocations().size() );
		s.getTransaction().commit();
		s.clear();
		
		s.beginTransaction();
		Iterator<Location> itr = q.getIncludedLocations().iterator();
		itr.next();
		itr.remove();
		s.update( q );
		s.getTransaction().commit();
		s.clear();
		
		s.beginTransaction();
		q = (Query) s.get( Query.class, q.getId() );
		assertEquals( 1, q.getIncludedLocations().size() );
		s.delete( q );
		s.getTransaction().commit();
		s.close();
	}
