	@Test
	public void testBasicLoadLoadPlanBuilding() {
		EntityManager em = getOrCreateEntityManager();
		EntityGraph eg = em.createEntityGraph( Cat.class );
		LoadPlan plan = buildLoadPlan( eg, Mode.LOAD, Cat.class );
		LoadPlanTreePrinter.INSTANCE.logTree( plan, new AliasResolutionContextImpl( sfi() ) );
		QuerySpace rootQuerySpace = plan.getQuerySpaces().getRootQuerySpaces().get( 0 );
		assertFalse(
				"With fetchgraph property and an empty EntityGraph, there should be no join at all",
				rootQuerySpace.getJoins().iterator().hasNext()
		);
		// -------------------------------------------------- another a little more complicated case
		eg = em.createEntityGraph( Cat.class );
		eg.addSubgraph( "owner", Person.class );
		plan = buildLoadPlan( eg, Mode.LOAD, Cat.class );
		LoadPlanTreePrinter.INSTANCE.logTree( plan, new AliasResolutionContextImpl( sfi() ) );
		rootQuerySpace = plan.getQuerySpaces().getRootQuerySpaces().get( 0 );
		Iterator<Join> iterator = rootQuerySpace.getJoins().iterator();
		assertTrue(
				"With fetchgraph property and an empty EntityGraph, there should be no join at all", iterator.hasNext()
		);
		Join personJoin = iterator.next();
		assertNotNull( personJoin );
		QuerySpace.Disposition disposition = personJoin.getRightHandSide().getDisposition();
		assertEquals(
				"This should be an entity join which fetches Person", QuerySpace.Disposition.ENTITY, disposition
		);

		iterator = personJoin.getRightHandSide().getJoins().iterator();
		assertTrue( "The composite address should be fetched", iterator.hasNext() );
		Join addressJoin = iterator.next();
		assertNotNull( addressJoin );
		disposition = addressJoin.getRightHandSide().getDisposition();
		assertEquals( QuerySpace.Disposition.COMPOSITE, disposition );
		iterator = addressJoin.getRightHandSide().getJoins().iterator();
		assertTrue( iterator.hasNext() );
		Join countryJoin = iterator.next();
		assertNotNull( countryJoin );
		disposition = countryJoin.getRightHandSide().getDisposition();
		assertEquals( QuerySpace.Disposition.ENTITY, disposition );
		assertFalse(
				"The ManyToOne attribute in composite should not be fetched",
				countryJoin.getRightHandSide().getJoins().iterator().hasNext()
		);
		em.close();
	}
