	@Test
	public void testBasicElementCollections() {
		EntityManager em = getOrCreateEntityManager();
		EntityGraph eg = em.createEntityGraph( Dog.class );
		eg.addAttributeNodes( "favorites" );
		LoadPlan loadLoadPlan = buildLoadPlan( eg, Mode.LOAD, Dog.class ); //WTF name!!!
		LoadPlanTreePrinter.INSTANCE.logTree( loadLoadPlan, new AliasResolutionContextImpl( sfi() ) );
		QuerySpace querySpace = loadLoadPlan.getQuerySpaces().getRootQuerySpaces().iterator().next();
		Iterator<Join> iterator = querySpace.getJoins().iterator();
		assertTrue( iterator.hasNext() );
		Join collectionJoin = iterator.next();
		assertEquals( QuerySpace.Disposition.COLLECTION, collectionJoin.getRightHandSide().getDisposition() );
		assertFalse( iterator.hasNext() );
		//----------------------------------------------------------------
		LoadPlan fetchLoadPlan = buildLoadPlan( eg, Mode.FETCH, Dog.class );
		LoadPlanTreePrinter.INSTANCE.logTree( fetchLoadPlan, new AliasResolutionContextImpl( sfi() ) );
		querySpace = fetchLoadPlan.getQuerySpaces().getRootQuerySpaces().iterator().next();
		iterator = querySpace.getJoins().iterator();
		assertTrue( iterator.hasNext() );
		collectionJoin = iterator.next();
		assertEquals( QuerySpace.Disposition.COLLECTION, collectionJoin.getRightHandSide().getDisposition() );
		assertFalse( iterator.hasNext() );
		em.close();
	}
