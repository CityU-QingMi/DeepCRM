	@Override
	public Object extract(CallableStatement statement, String[] paramNames, SharedSessionContractImplementor session)
			throws SQLException {
		if ( canDoExtraction() ) {
			return ((ProcedureParameterExtractionAware) userType).extract( statement, paramNames, session );
		}
		else {
			throw new UnsupportedOperationException(
					"Type [" + userType + "] does support parameter value extraction"
			);
		}
	}
