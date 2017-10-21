	@Override
	protected final void compileInternal() {
		if (isSqlReadyForUse()) {
			this.callString = getSql();
		}
		else {
			List<SqlParameter> parameters = getDeclaredParameters();
			int parameterCount = 0;
			if (isFunction()) {
				this.callString = "{? = call " + getSql() + "(";
				parameterCount = -1;
			}
			else {
				this.callString = "{call " + getSql() + "(";
			}
			for (SqlParameter parameter : parameters) {
				if (!(parameter.isResultsParameter())) {
					if (parameterCount > 0) {
						this.callString += ", ";
					}
					if (parameterCount >= 0) {
						this.callString += "?";
					}
					parameterCount++;
				}
			}
			this.callString += ")}";
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Compiled stored procedure. Call string is [" + this.callString + "]");
		}

		this.callableStatementFactory = new CallableStatementCreatorFactory(this.callString, getDeclaredParameters());
		this.callableStatementFactory.setResultSetType(getResultSetType());
		this.callableStatementFactory.setUpdatableResults(isUpdatableResults());

		onCompileInternal();
	}
