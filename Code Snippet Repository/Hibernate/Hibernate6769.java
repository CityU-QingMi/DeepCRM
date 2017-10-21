	@Test
	public void testJoinedSubclassManyToMany() throws Exception {
		Session s = openSession();
		Zone a = new Zone();
		InspectorPrefixes ip = new InspectorPrefixes( "dgi" );
		Transaction tx = s.beginTransaction();
		s.save( a );
		s.save( ip );
		ip.getAreas().add( a );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		ip = (InspectorPrefixes) s.get( InspectorPrefixes.class, ip.getId() );
		assertNotNull( ip );
		assertEquals( 1, ip.getAreas().size() );
		assertEquals( a.getId(), ip.getAreas().get( 0 ).getId() );
		s.delete( ip );
		s.delete( ip.getAreas().get( 0 ) );
		tx.commit();
		s.close();
	}
