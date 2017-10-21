	protected QueryImplementor createQuery(NamedQueryDefinition queryDefinition) {
		String queryString = queryDefinition.getQueryString();
		final QueryImpl query = new QueryImpl(
				this,
				getQueryPlan( queryString, false ).getParameterMetadata(),
				queryString
		);
		query.setHibernateFlushMode( queryDefinition.getFlushMode() );
		query.setComment( queryDefinition.getComment() != null ? queryDefinition.getComment() : queryDefinition.getName() );
		if ( queryDefinition.getLockOptions() != null ) {
			query.setLockOptions( queryDefinition.getLockOptions() );
		}

		initQueryFromNamedDefinition( query, queryDefinition );
//		applyQuerySettingsAndHints( query );

		return query;
	}
