	@Override
	protected ScrollableResultsImplementor doScroll(ScrollMode scrollMode) {
		final NativeSQLQuerySpecification nativeSQLQuerySpecification = generateQuerySpecification();
		final QueryParameters queryParameters = getQueryParameters();
		queryParameters.setScrollMode( scrollMode );
		return getProducer().scroll(
				nativeSQLQuerySpecification,
				queryParameters
		);
	}
