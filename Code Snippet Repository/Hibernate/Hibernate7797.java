	@Test
	public void testCollectionDefaultFetch() {
		final AssociationType associationType = determineAssociationType( AnEntity.class, "colorsDefault" );
		final org.hibernate.FetchMode fetchMode = determineFetchMode( AnEntity.class, "colorsDefault" );
		assertSame( org.hibernate.FetchMode.SELECT, fetchMode );
		final FetchStyle fetchStyle = FetchStrategyHelper.determineFetchStyleByMetadata(
				fetchMode,
				associationType,
				sessionFactory()
		);
		assertSame( FetchStyle.BATCH, fetchStyle );
		final FetchTiming fetchTiming = FetchStrategyHelper.determineFetchTiming(
				fetchStyle,
				associationType,
				sessionFactory()
		);
		assertSame( FetchTiming.DELAYED, fetchTiming );
	}
