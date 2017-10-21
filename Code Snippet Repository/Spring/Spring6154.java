	@Override
	public Map<String, Object> call(CallableStatementCreator csc, List<SqlParameter> declaredParameters)
			throws DataAccessException {

		final List<SqlParameter> updateCountParameters = new ArrayList<>();
		final List<SqlParameter> resultSetParameters = new ArrayList<>();
		final List<SqlParameter> callParameters = new ArrayList<>();

		for (SqlParameter parameter : declaredParameters) {
			if (parameter.isResultsParameter()) {
				if (parameter instanceof SqlReturnResultSet) {
					resultSetParameters.add(parameter);
				}
				else {
					updateCountParameters.add(parameter);
				}
			}
			else {
				callParameters.add(parameter);
			}
		}

		Map<String, Object> result = execute(csc, cs -> {
			boolean retVal = cs.execute();
			int updateCount = cs.getUpdateCount();
			if (logger.isDebugEnabled()) {
				logger.debug("CallableStatement.execute() returned '" + retVal + "'");
				logger.debug("CallableStatement.getUpdateCount() returned " + updateCount);
			}
			Map<String, Object> returnedResults = createResultsMap();
			if (retVal || updateCount != -1) {
				returnedResults.putAll(extractReturnedResults(cs, updateCountParameters, resultSetParameters, updateCount));
			}
			returnedResults.putAll(extractOutputParameters(cs, callParameters));
			return returnedResults;
		});

		Assert.state(result != null, "No result map");
		return result;
	}
