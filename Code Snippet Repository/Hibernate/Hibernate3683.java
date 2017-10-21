	@Override
	protected int bindParameterValues(
			final PreparedStatement statement,
			final QueryParameters queryParameters,
			final int startIndex,
			final SharedSessionContractImplementor session) throws SQLException {
		int position = startIndex;
		List<ParameterSpecification> parameterSpecs = queryTranslator.getCollectedParameterSpecifications();
		for ( ParameterSpecification spec : parameterSpecs ) {
			position += spec.bind( statement, queryParameters, session, position );
		}
		return position - startIndex;
	}
