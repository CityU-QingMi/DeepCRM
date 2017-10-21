	public QueryParameters getQueryParameters() {
		final HQLQueryPlan entityGraphHintedQueryPlan;
		if ( entityGraphQueryHint == null) {
			entityGraphHintedQueryPlan = null;
		}
		else {
			queryParameterBindings.verifyParametersBound( false );

			// todo : ideally we'd update the instance state related to queryString but that is final atm

			final String expandedQuery = queryParameterBindings.expandListValuedParameters( getQueryString(), getProducer() );
			entityGraphHintedQueryPlan = new HQLQueryPlan(
					expandedQuery,
					false,
					getProducer().getLoadQueryInfluencers().getEnabledFilters(),
					getProducer().getFactory(),
					entityGraphQueryHint
			);
		}

		QueryParameters queryParameters = new QueryParameters(
				getPositionalParameterTypes(),
				getPositionalParameterValues(),
				getNamedParameterMap(),
				getLockOptions(),
				queryOptions,
				true,
				isReadOnly(),
				cacheable,
				cacheRegion,
				comment,
				dbHints,
				null,
				optionalObject,
				optionalEntityName,
				optionalId,
				resultTransformer
		);
		queryParameters.setQueryPlan( entityGraphHintedQueryPlan );
		if ( passDistinctThrough != null ) {
			queryParameters.setPassDistinctThrough( passDistinctThrough );
		}
		return queryParameters;
	}
