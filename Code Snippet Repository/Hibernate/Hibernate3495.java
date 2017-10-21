	private void createSubselects(List keys, QueryParameters queryParameters, SharedSessionContractImplementor session) {
		if ( keys.size() > 1 ) { //if we only returned one entity, query by key is more efficient

			Set[] keySets = transpose( keys );

			Map namedParameterLocMap = buildNamedParameterLocMap( queryParameters );

			final Loadable[] loadables = getEntityPersisters();
			final String[] aliases = getAliases();
			final String subselectQueryString = SubselectFetch.createSubselectFetchQueryFragment( queryParameters );
			for ( Object key : keys ) {
				final EntityKey[] rowKeys = (EntityKey[]) key;
				for ( int i = 0; i < rowKeys.length; i++ ) {

					if ( rowKeys[i] != null && loadables[i].hasSubselectLoadableCollections() ) {

						SubselectFetch subselectFetch = new SubselectFetch(
								subselectQueryString,
								aliases[i],
								loadables[i],
								queryParameters,
								keySets[i],
								namedParameterLocMap
						);

						session.getPersistenceContext()
								.getBatchFetchQueue()
								.addSubselect( rowKeys[i], subselectFetch );
					}

				}

			}
		}
	}
