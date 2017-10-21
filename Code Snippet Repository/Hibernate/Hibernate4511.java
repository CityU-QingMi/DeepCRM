	public NativeQueryImpl(
			String sqlString,
			boolean callable,
			SharedSessionContractImplementor session,
			ParameterMetadata sqlParameterMetadata) {
		super( session, sqlParameterMetadata );

		this.queryReturns = new ArrayList<>();
		this.sqlString = sqlString;
		this.callable = callable;
		this.querySpaces = new ArrayList<>();
	}
