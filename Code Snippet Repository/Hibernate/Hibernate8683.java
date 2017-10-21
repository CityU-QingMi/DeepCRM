	@Test
	public void testCascadeBasedBuild() {
		EntityPersister ep = (EntityPersister) sessionFactory().getClassMetadata(Message.class);
		CascadeStyleLoadPlanBuildingAssociationVisitationStrategy strategy = new CascadeStyleLoadPlanBuildingAssociationVisitationStrategy(
				CascadingActions.MERGE,
				sessionFactory(),
				LoadQueryInfluencers.NONE,
				LockMode.NONE
		);
		LoadPlan plan = MetamodelDrivenLoadPlanBuilder.buildRootEntityLoadPlan( strategy, ep );
		assertFalse( plan.hasAnyScalarReturns() );
		assertEquals( 1, plan.getReturns().size() );
		Return rtn = plan.getReturns().get( 0 );
		EntityReturn entityReturn = ExtraAssertions.assertTyping( EntityReturn.class, rtn );
		assertNotNull( entityReturn.getFetches() );
		assertEquals( 1, entityReturn.getFetches().length );
		Fetch fetch = entityReturn.getFetches()[0];
		EntityFetch entityFetch = ExtraAssertions.assertTyping( EntityFetch.class, fetch );
		assertNotNull( entityFetch.getFetches() );
		assertEquals( 0, entityFetch.getFetches().length );

		LoadPlanTreePrinter.INSTANCE.logTree( plan, new AliasResolutionContextImpl( sessionFactory() ) );
	}
