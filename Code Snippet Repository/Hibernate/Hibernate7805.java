	@Test
	public void testCollectionSubselectFetch() {
		final AssociationType associationType = determineAssociationType( AnEntity.class, "colorsSubselect" );
		final org.hibernate.FetchMode fetchMode = determineFetchMode( AnEntity.class, "colorsSubselect" );
		assertSame( org.hibernate.FetchMode.SELECT, fetchMode );
		final FetchStyle fetchStyle = FetchStrategyHelper.determineFetchStyleByMetadata(
				fetchMode,
				associationType,
				sessionFactory()
		);
		assertSame( FetchStyle.SUBSELECT, fetchStyle );
		final FetchTiming fetchTiming = FetchStrategyHelper.determineFetchTiming(
				fetchStyle,
				associationType,
				sessionFactory()
		);
		assertSame( FetchTiming.DELAYED, fetchTiming );
	}
