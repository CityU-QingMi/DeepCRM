	@Test
	public void testManyToMany() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();

		House whiteHouse = new House();
		whiteHouse.setAddress( "1600 Pennsylvania Avenue, Washington" );
		Inhabitant bill = new Inhabitant();
		bill.setName( "Bill Clinton" );
		Inhabitant george = new Inhabitant();
		george.setName( "George W Bush" );
		s.persist( george );
		s.persist( bill );
		whiteHouse.getHasInhabitants().add( bill );
		whiteHouse.getHasInhabitants().add( george );
		//bill.getLivesIn().add( whiteHouse );
		//george.getLivesIn().add( whiteHouse );

		s.persist( whiteHouse );
		tx.commit();
		s = openSession();
		tx = s.beginTransaction();

		whiteHouse = (House) s.get( House.class, whiteHouse.getId() );
		assertNotNull( whiteHouse );
		assertEquals( 2, whiteHouse.getHasInhabitants().size() );

		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		bill = (Inhabitant) s.get( Inhabitant.class, bill.getId() );
		assertNotNull( bill );
		assertEquals( 1, bill.getLivesIn().size() );
		assertEquals( whiteHouse.getAddress(), bill.getLivesIn().iterator().next().getAddress() );

		whiteHouse = bill.getLivesIn().iterator().next();
		s.delete( whiteHouse );
		Iterator it = whiteHouse.getHasInhabitants().iterator();
		while ( it.hasNext() ) {
			s.delete( it.next() );
		}
		tx.commit();
		s.close();
	}
