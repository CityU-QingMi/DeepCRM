	@SuppressWarnings({"", ""})
	protected <T> NativeQueryImplementor createNativeQuery(NamedSQLQueryDefinition queryDefinition, Class<T> resultType) {
		if ( resultType != null && !Tuple.class.equals(resultType)) {
			resultClassChecking( resultType, queryDefinition );
		}

		final NativeQueryImpl query = new NativeQueryImpl(
				queryDefinition,
				this,
				factory.getQueryPlanCache().getSQLParameterMetadata( queryDefinition.getQueryString(), false )
		);
		if (Tuple.class.equals(resultType)) {
			query.setResultTransformer(new NativeQueryTupleTransformer());
		}
		query.setHibernateFlushMode( queryDefinition.getFlushMode() );
		query.setComment( queryDefinition.getComment() != null ? queryDefinition.getComment() : queryDefinition.getName() );
		if ( queryDefinition.getLockOptions() != null ) {
			query.setLockOptions( queryDefinition.getLockOptions() );
		}

		initQueryFromNamedDefinition( query, queryDefinition );
		applyQuerySettingsAndHints( query );

		return query;
	}
