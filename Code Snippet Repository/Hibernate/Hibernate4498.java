	@SuppressWarnings("")
	private <P> void setParameter(Parameter<P> parameter, Object value, Type type) {
		if ( parameter instanceof QueryParameter ) {
			setParameter( (QueryParameter) parameter, value, type );
		}
		else if ( value == null ) {
			locateBinding( parameter ).setBindValue( null, type );
		}
		else if ( value instanceof Collection && !isRegisteredAsBasicType( value.getClass() ) ) {
			locateListBinding( parameter ).setBindValues( (Collection) value, type );
		}
		else {
			locateBinding( parameter ).setBindValue( (P) value, type );
		}
	}
