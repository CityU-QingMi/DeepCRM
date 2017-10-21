	@Test
	public void testCollectionJoinFetch() {
		final AssociationType associationType = determineAssociationType( AnEntity.class, "colorsJoin" );
		final org.hibernate.FetchMode fetchMode = determineFetchMode( AnEntity.class, "colorsJoin" );
		assertSame( org.hibernate.FetchMode.JOIN, fetchMode );
		final FetchStyle fetchStyle = FetchStrategyHelper.determineFetchStyleByMetadata(
				fetchMode,
				associationType,
				sessionFactory()
		);
		assertSame( FetchStyle.JOIN, fetchStyle );
		final FetchTiming fetchTiming = FetchStrategyHelper.determineFetchTiming(
				fetchStyle,
				associationType,
				sessionFactory()
		);
		assertSame( FetchTiming.IMMEDIATE, fetchTiming );
	}
