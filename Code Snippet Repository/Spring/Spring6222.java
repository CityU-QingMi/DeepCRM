	private Map<String, Object> executeCallInternal(Map<String, ?> args) {
		CallableStatementCreator csc = getCallableStatementFactory().newCallableStatementCreator(args);
		if (logger.isDebugEnabled()) {
			logger.debug("The following parameters are used for call " + getCallString() + " with " + args);
			int i = 1;
			for (SqlParameter param : getCallParameters()) {
				logger.debug(i + ": " +  param.getName() + ", SQL type "+ param.getSqlType() + ", type name " +
						param.getTypeName() + ", parameter class [" + param.getClass().getName() + "]");
				i++;
			}
		}
		return getJdbcTemplate().call(csc, getCallParameters());
	}
