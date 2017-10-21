	@Test
	public void testDefaultCollectionTable() throws Exception {
		//are the tables correct?
		assertTrue( SchemaUtil.isTablePresent( "WealthyPerson_vacationHomes", metadata() ) );
		assertTrue( SchemaUtil.isTablePresent( "WealthyPerson_legacyVacationHomes", metadata() ) );
		assertTrue( SchemaUtil.isTablePresent( "WelPers_VacHomes", metadata() ) );

		//just to make sure, use the mapping
		Session s;
		Transaction tx;
		WealthyPerson p = new WealthyPerson();
		Address a = new Address();
		Address vacation = new Address();
		Country c = new Country();
		Country bornCountry = new Country();
		c.setIso2( "DM" );
		c.setName( "Matt Damon Land" );
		bornCountry.setIso2( "US" );
		bornCountry.setName( "United States of America" );

		a.address1 = "colorado street";
		a.city = "Springfield";
		a.country = c;
		vacation.address1 = "rock street";
		vacation.city = "Plymouth";
		vacation.country = c;
		p.vacationHomes.add( vacation );
		p.address = a;
		p.bornIn = bornCountry;
		p.name = "Homer";
		s = openSession();
		tx = s.beginTransaction();
		s.persist( p );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		p = (WealthyPerson) s.get( WealthyPerson.class, p.id );
		assertNotNull( p );
		assertNotNull( p.address );
		assertEquals( "Springfield", p.address.city );
		assertNotNull( p.address.country );
		assertEquals( "DM", p.address.country.getIso2() );
		assertNotNull( p.bornIn );
		assertEquals( "US", p.bornIn.getIso2() );
		tx.commit();
		s.close();
	}
