	public Map<String, Object> execute(Object... inParams) {
		Map<String, Object> paramsToUse = new HashMap<>();
		validateParameters(inParams);
		int i = 0;
		for (SqlParameter sqlParameter : getDeclaredParameters()) {
			if (sqlParameter.isInputValueProvided()) {
				if (i < inParams.length) {
					paramsToUse.put(sqlParameter.getName(), inParams[i++]);
				}
			}
		}
		return getJdbcTemplate().call(newCallableStatementCreator(paramsToUse), getDeclaredParameters());
	}
