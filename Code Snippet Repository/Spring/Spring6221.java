	protected void compileInternal() {
		DataSource dataSource = getJdbcTemplate().getDataSource();
		Assert.state(dataSource != null, "No DataSource set");
		this.callMetaDataContext.initializeMetaData(dataSource);

		// Iterate over the declared RowMappers and register the corresponding SqlParameter
		for (Map.Entry<String, RowMapper<?>> entry : this.declaredRowMappers.entrySet()) {
			SqlParameter resultSetParameter =
					this.callMetaDataContext.createReturnResultSetParameter(entry.getKey(), entry.getValue());
			this.declaredParameters.add(resultSetParameter);
		}
		this.callMetaDataContext.processParameters(this.declaredParameters);

		this.callString = this.callMetaDataContext.createCallString();
		if (logger.isDebugEnabled()) {
			logger.debug("Compiled stored procedure. Call string is [" + this.callString + "]");
		}

		this.callableStatementFactory =
				new CallableStatementCreatorFactory(this.callString, this.callMetaDataContext.getCallParameters());

		onCompileInternal();
	}
