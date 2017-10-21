	private NativeQueryImplementor getNativeQueryImplementor(
			NamedSQLQueryDefinition queryDefinition,
			ParameterMetadata parameterMetadata) {
		final NativeQueryImpl query = new NativeQueryImpl(
				queryDefinition,
				this,
				parameterMetadata
		);
		query.setComment( queryDefinition.getComment() != null ? queryDefinition.getComment() : queryDefinition.getName() );

		initQueryFromNamedDefinition( query, queryDefinition );
		applyQuerySettingsAndHints( query );

		return query;
	}
