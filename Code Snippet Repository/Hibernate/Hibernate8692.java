	@Test
	public void testEncapsulatedCompositeIdWithFetches1() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass( Card.class );
		cfg.addAnnotatedClass( CardField.class );
		cfg.addAnnotatedClass( Key.class );
		cfg.addAnnotatedClass( PrimaryKey.class );

		SessionFactoryImplementor sf = (SessionFactoryImplementor) cfg.buildSessionFactory();

		try {
			final OuterJoinLoadable cardFieldPersister = (OuterJoinLoadable) sf.getClassMetadata( CardField.class );
			doCompare( sf, cardFieldPersister );

			final LoadPlan loadPlan = LoadPlanStructureAssertionHelper.INSTANCE.buildLoadPlan( sf, cardFieldPersister );
			assertEquals( LoadPlan.Disposition.ENTITY_LOADER, loadPlan.getDisposition() );
			assertEquals( 1, loadPlan.getReturns().size() );
			final EntityReturn cardFieldReturn = assertTyping( EntityReturn.class, loadPlan.getReturns().get( 0 ) );
			assertEquals( 0, cardFieldReturn.getFetches().length );

			// CardField defines a composite pk with 2 many-to-ones : Card and Key (the id description acts as the composite);
			// because it is an @EmbeddedId, the ID provided by the application is used "as is"
			// and fetches are not included in the load plan.
			assertFalse( cardFieldReturn.getIdentifierDescription().hasFetches() );

			// we need the readers ordered in a certain manner.  Here specifically: Fetch(Card), Fetch(Key), Return(CardField)
			//
			// additionally, we need Fetch(Card) and Fetch(Key) to be hydrated/semi-resolved before attempting to
			// resolve the EntityKey for Return(CardField)
			//
			// together those sound like argument enough to continue keeping readers for "identifier fetches" as part of
			// a special "identifier reader".  generated aliases could help here too to remove cyclic-ness from the graph.
			// but at any rate, we need to know still when this becomes circularity
		}
		finally {
			sf.close();
		}
	}
