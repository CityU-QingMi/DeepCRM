	public SubselectFetch(
			final String alias,
			final Loadable loadable,
			final QueryParameters queryParameters,
			final Set resultingEntityKeys,
			final Map namedParameterLocMap) {
		this(
				createSubselectFetchQueryFragment( queryParameters ),
				alias,
				loadable,
				queryParameters,
				resultingEntityKeys,
				namedParameterLocMap
		);
	}
