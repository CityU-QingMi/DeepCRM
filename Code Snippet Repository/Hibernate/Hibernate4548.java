	@Override
	@SuppressWarnings("")
	public <T> QueryParameter<T> resolve(Parameter<T> param) {
		// first see if that instance exists here...
		for ( ProcedureParameter parameter : parameters ) {
			if ( parameter == param ) {
				return parameter;
			}
		}

		// otherwise, try name/position from the incoming param
		if ( param.getPosition() != null || param.getName() != null ) {
			for ( ProcedureParameter parameter : parameters ) {
				// name
				if ( param.getName() != null && param.getName().equals( parameter.getName() ) ) {
					return parameter;
				}

				// position
				if ( param.getPosition() != null && param.getPosition().equals( parameter.getPosition() ) ) {
					return parameter;
				}
			}
		}

		return null;
	}
