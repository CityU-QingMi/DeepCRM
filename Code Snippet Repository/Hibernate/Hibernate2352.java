	public SubselectFetch(
			final String subselectFetchQueryFragment,
			final String alias,
			final Loadable loadable,
			final QueryParameters queryParameters,
			final Set resultingEntityKeys,
			final Map namedParameterLocMap) {
		this.resultingEntityKeys = resultingEntityKeys;
		this.queryParameters = queryParameters;
		this.namedParameterLocMap = namedParameterLocMap;
		this.loadable = loadable;
		this.alias = alias;

		this.queryString = subselectFetchQueryFragment;
	}
