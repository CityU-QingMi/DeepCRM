	public ProcedureCallMementoImpl(
			String procedureName,
			NativeSQLQueryReturn[] queryReturns,
			ParameterStrategy parameterStrategy,
			List<ParameterMemento> parameterDeclarations,
			Set<String> synchronizedQuerySpaces,
			Map<String, Object> hintsMap) {
		this.procedureName = procedureName;
		this.queryReturns = queryReturns;
		this.parameterStrategy = parameterStrategy;
		this.parameterDeclarations = parameterDeclarations;
		this.synchronizedQuerySpaces = synchronizedQuerySpaces;
		this.hintsMap = hintsMap;
	}
