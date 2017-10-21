	public void testCache() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Plane plane = new Plane();
		plane.setNbrOfSeats( 5 );
		s.persist( plane );
		tx.commit();
		s.close();
		sessionFactory().getStatistics().clear();
		sessionFactory().getStatistics().setStatisticsEnabled( true );
		s = openSession();
		tx = s.beginTransaction();
		Query query = s.getNamedQuery( "plane.byId" ).setParameter( "id", plane.getId() );
		plane = (Plane) query.uniqueResult();
		assertEquals( 1, sessionFactory().getStatistics().getQueryCachePutCount() );
		plane = (Plane) s.getNamedQuery( "plane.byId" ).setParameter( "id", plane.getId() ).uniqueResult();
		assertEquals( 1, sessionFactory().getStatistics().getQueryCacheHitCount() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		s.delete( s.get( Plane.class, plane.getId() ) );
		tx.commit();
		s.close();
	}
