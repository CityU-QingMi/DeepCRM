	@Test
	public void testJoinedSubclassManyToManyWithNonPkReference() throws Exception {
		Session s = openSession();
		Zone a = new Zone();
		InspectorPrefixes ip = new InspectorPrefixes( "dgi" );
		ip.setName( "Inspector" );
		Transaction tx = s.beginTransaction();
		s.save( a );
		s.save( ip );
		ip.getDesertedAreas().add( a );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		ip = (InspectorPrefixes) s.get( InspectorPrefixes.class, ip.getId() );
		assertNotNull( ip );
		assertEquals( 1, ip.getDesertedAreas().size() );
		assertEquals( a.getId(), ip.getDesertedAreas().get( 0 ).getId() );
		s.delete( ip );
		s.delete( ip.getDesertedAreas().get( 0 ) );
		tx.commit();
		s.close();
	}
