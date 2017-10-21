	private AbstractParameterRegistrationImpl(
			ProcedureCallImpl procedureCall,
			Integer position,
			String name,
			ParameterMode mode,
			Class<T> type,
			Type hibernateType,
			boolean initialPassNullsSetting) {
		this.procedureCall = procedureCall;

		this.position = position;
		this.name = name;

		this.mode = mode;
		this.type = type;

		if ( mode == ParameterMode.REF_CURSOR ) {
			this.sqlTypes = new int[]{ Types.REF_CURSOR };
		}
		else {
			this.passNulls = initialPassNullsSetting;
			setHibernateType( hibernateType );
		}
	}
