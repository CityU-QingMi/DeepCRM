	@Test
	@RequiresDialectFeature( DialectChecks.SupportsExpectedLobUsagePattern.class )
	public void testSerialized() throws Exception {
		Forest forest = new Forest();
		forest.setName( "Shire" );
		Country country = new Country();
		country.setName( "Middle Earth" );
		forest.setCountry( country );
		Set<Country> near = new HashSet<Country>();
		country = new Country();
		country.setName("Mordor");
		near.add(country);
		country = new Country();
		country.setName("Gondor");
		near.add(country);
		country = new Country();
		country.setName("Eriador");
		near.add(country);
		forest.setNear(near);
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( forest );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		forest = (Forest) s.get( Forest.class, forest.getId() );
		assertNotNull( forest );
		country = forest.getCountry();
		assertNotNull( country );
		assertEquals( country.getName(), forest.getCountry().getName() );
		near = forest.getNear();
		assertTrue("correct number of nearby countries", near.size() == 3);
		for (Iterator iter = near.iterator(); iter.hasNext();) {
			country = (Country)iter.next();
			String name = country.getName();
			assertTrue("found expected nearby country " + name,
				(name.equals("Mordor") || name.equals("Gondor") || name.equals("Eriador")));
		}
		tx.commit();
		s.close();
	}
