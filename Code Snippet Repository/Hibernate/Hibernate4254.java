	@Override
	public ProcedureCallMemento extractMemento(Map<String, Object> hints) {
		return new ProcedureCallMementoImpl(
				procedureName,
				Util.copy( queryReturns ),
				parameterStrategy,
				toParameterMementos( registeredParameters ),
				Util.copy( synchronizedQuerySpaces ),
				Util.copy( hints )
		);
	}
