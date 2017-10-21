	@Test
	public void testCollectionInitializerCase() {
		CollectionPersister cp = sessionFactory().getCollectionPersister( Poster.class.getName() + ".messages" );
		FetchStyleLoadPlanBuildingAssociationVisitationStrategy strategy = new FetchStyleLoadPlanBuildingAssociationVisitationStrategy(
				sessionFactory(),
				LoadQueryInfluencers.NONE,
				LockMode.NONE
		);
		LoadPlan plan = MetamodelDrivenLoadPlanBuilder.buildRootCollectionLoadPlan( strategy, cp );
		assertFalse( plan.hasAnyScalarReturns() );
		assertEquals( 1, plan.getReturns().size() );
		Return rtn = plan.getReturns().get( 0 );
		CollectionReturn collectionReturn = ExtraAssertions.assertTyping( CollectionReturn.class, rtn );

		assertNotNull( collectionReturn.getElementGraph() );
		assertNotNull( collectionReturn.getElementGraph().getFetches() );
		// the collection Message elements are fetched, but Message.poster is not fetched
		// (because that collection is owned by that Poster)
		assertEquals( 0, collectionReturn.getElementGraph().getFetches().length );
		EntityReference entityReference = ExtraAssertions.assertTyping( EntityReference.class, collectionReturn.getElementGraph() );
		assertNotNull( entityReference.getFetches() );
		assertEquals( 0, entityReference.getFetches().length );

		LoadPlanTreePrinter.INSTANCE.logTree( plan, new AliasResolutionContextImpl( sessionFactory() ) );
	}
