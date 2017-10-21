	protected CteValuesListBuilder prepareCteStatement(
			SharedSessionContractImplementor session,
			QueryParameters queryParameters) {

		return new CteValuesListBuilder(
				determineIdTableName( getTargetedQueryable() ),
				getTargetedQueryable().getIdentifierColumnNames(),
				selectIds( session, queryParameters )
		);
	}
