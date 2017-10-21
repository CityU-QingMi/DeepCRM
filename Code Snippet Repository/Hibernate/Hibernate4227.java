	private AbstractParameterRegistrationImpl(
			ProcedureCallImpl procedureCall,
			Integer position,
			String name,
			ParameterMode mode,
			Class<T> type,
			boolean initialPassNullsSetting) {
		this(
				procedureCall,
				position,
				name,
				mode,
				type,
				procedureCall.getSession().getFactory().getTypeResolver().heuristicType( type.getName() ),
				initialPassNullsSetting
		);
	}
