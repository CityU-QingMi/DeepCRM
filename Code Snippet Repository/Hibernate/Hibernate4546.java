	@Override
	@SuppressWarnings("")
	public <T> QueryParameter<T> getQueryParameter(String name) {
		assert name != null;
		QueryParameter<T> result = null;
		if ( hasNamed ) {
			for ( ProcedureParameter parameter : parameters ) {
				if ( name.equals( parameter.getName() ) ) {
					result = parameter;
					break;
				}
			}
		}
		if ( result != null ) {
			return result;
		}
		throw new QueryParameterException( "could not locate named parameter [" + name + "]" );
	}
