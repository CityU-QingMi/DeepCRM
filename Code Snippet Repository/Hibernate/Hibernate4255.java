	@Override
	public ProcedureCallMemento extractMemento() {
		return new ProcedureCallMementoImpl(
				procedureName,
				Util.copy( queryReturns ),
				parameterStrategy,
				toParameterMementos( registeredParameters ),
				Util.copy( synchronizedQuerySpaces ),
				Util.copy( getHints() )
		);
	}
