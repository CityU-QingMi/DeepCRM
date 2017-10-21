	@Test
	public void testBasicFetchLoadPlanBuilding() {
		EntityManager em = getOrCreateEntityManager();
		EntityGraph eg = em.createEntityGraph( Cat.class );
		LoadPlan plan = buildLoadPlan( eg, Mode.FETCH, Cat.class );
		LoadPlanTreePrinter.INSTANCE.logTree( plan, new AliasResolutionContextImpl( sfi() ) );
		QuerySpace rootQuerySpace = plan.getQuerySpaces().getRootQuerySpaces().get( 0 );
		assertFalse(
				"With fetchgraph property and an empty EntityGraph, there should be no join at all",
				rootQuerySpace.getJoins().iterator().hasNext()
		);
		// -------------------------------------------------- another a little more complicated case
		eg = em.createEntityGraph( Cat.class );
		eg.addSubgraph( "owner", Person.class );
		plan = buildLoadPlan( eg, Mode.FETCH, Cat.class );
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
		assertFalse( iterator.hasNext() );
		assertFalse(
				"The ManyToOne attribute in composite should not be fetched",
				addressJoin.getRightHandSide().getJoins().iterator().hasNext()
		);
		em.close();
	}
