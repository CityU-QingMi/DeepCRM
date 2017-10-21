	private void createSubselects() {
		if ( subselectLoadableEntityKeyMap == null || nRowsRead <= 1 ) {
			LOG.tracef(
					"Skipping create subselects because there are fewer than 2 results, so query by key is more efficient.",
					getClass().getName()
			);
			return; // early return
		}

		final Map<String, int[]> namedParameterLocMap =
				ResultSetProcessorHelper.buildNamedParameterLocMap( queryParameters, namedParameterContext );

		final String subselectQueryString = SubselectFetch.createSubselectFetchQueryFragment( queryParameters );
		for ( Map.Entry<EntityReference, Set<EntityKey>> entry : subselectLoadableEntityKeyMap.entrySet() ) {
			if ( ! entry.getKey().getEntityPersister().hasSubselectLoadableCollections() ) {
				continue;
			}

			SubselectFetch subselectFetch = new SubselectFetch(
					subselectQueryString,
					aliasResolutionContext.resolveSqlTableAliasFromQuerySpaceUid( entry.getKey().getQuerySpaceUid() ),
					(Loadable) entry.getKey().getEntityPersister(),
					queryParameters,
					entry.getValue(),
					namedParameterLocMap
			);

			for ( EntityKey key : entry.getValue() ) {
				session.getPersistenceContext().getBatchFetchQueue().addSubselect( key, subselectFetch );
			}
		}
	}
