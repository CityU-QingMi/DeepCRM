	@Test
	public void testListWithBagSemanticAndOrderBy() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		City paris = new City();
		paris.setName( "Paris" );
		s.persist( paris );
		Street rochechoir = new Street();
		rochechoir.setStreetName( "Rochechoir" );
		rochechoir.setCity( paris );
		Street chmpsElysees = new Street();
		chmpsElysees.setStreetName( "Champs Elysees" );
		chmpsElysees.setCity( paris );
		Street grandeArmee = new Street();
		grandeArmee.setStreetName( "Grande Armee" );
		grandeArmee.setCity( paris );
		s.persist( rochechoir );
		s.persist( chmpsElysees );
		s.persist( grandeArmee );
		paris.addMainStreet( chmpsElysees );
		paris.addMainStreet( grandeArmee );

		s.flush();
		s.clear();

		// Assert the primary key value relationship amongst the 3 streets...
		Assert.assertTrue( rochechoir.getId() < chmpsElysees.getId() );
		Assert.assertTrue( chmpsElysees.getId() < grandeArmee.getId() );

		paris = ( City ) s.get( City.class, paris.getId() );

		// City.streets is defined to be ordered by name primarily...
		assertEquals( 3, paris.getStreets().size() );
		assertEquals( chmpsElysees.getStreetName(), paris.getStreets().get( 0 ).getStreetName() );
		assertEquals( grandeArmee.getStreetName(), paris.getStreets().get( 1 ).getStreetName() );
		// City.mainStreets is defined to be ordered by street id
		List<Street> mainStreets = paris.getMainStreets();
		assertEquals( 2, mainStreets.size() );
		Integer previousId = -1;
		for ( Street street : mainStreets ) {
			assertTrue( previousId < street.getId() );
			previousId = street.getId();
		}
		tx.rollback();
		s.close();

	}
