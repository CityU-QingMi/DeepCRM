	@Test
	public void testUnionSubclassFetchMode() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Location mel = new Location("Earth");
		s.save(mel);
		
		Human gavin = new Human();
		gavin.setIdentity("gavin");
		gavin.setSex('M');
		gavin.setLocation(mel);
		mel.addBeing(gavin);
		Human max = new Human();
		max.setIdentity( "max" );
		max.setSex( 'M' );
		max.setLocation(mel);
		mel.addBeing(gavin);
		
		s.flush();
		s.clear();
		
		List list = s.createCriteria(Human.class)
			.setFetchMode("location", FetchMode.JOIN)
			.setFetchMode("location.beings", FetchMode.JOIN)
			.list();

		for ( Object aList : list ) {
			Human h = (Human) aList;
			assertTrue( Hibernate.isInitialized( h.getLocation() ) );
			assertTrue( Hibernate.isInitialized( h.getLocation().getBeings() ) );
			s.delete( h );
		}
		s.delete( s.get( Location.class, mel.getId() ) );
		t.commit();
		s.close();
	}
