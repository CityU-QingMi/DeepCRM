	@Override
	@SuppressWarnings("")
	public <T> QueryParameter<T> getQueryParameter(Integer position) {
		assert position != null;

		if ( ordinalParamCount > 0 ) {
			for ( ProcedureParameter parameter : parameters ) {
				if ( parameter.getPosition() != null && position.intValue() == parameter.getPosition() ) {
					return parameter;
				}
			}
		}
		throw new QueryParameterException( "could not locate parameter at position [" + position + "]" );
	}
