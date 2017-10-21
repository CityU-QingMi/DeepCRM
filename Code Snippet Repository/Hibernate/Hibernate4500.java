	@Override
	@SuppressWarnings("")
	public QueryImplementor setParameter(int position, Object value) {
		if ( value instanceof TypedParameterValue ) {
			final TypedParameterValue typedParameterValue = (TypedParameterValue) value;
			setParameter( position, typedParameterValue.getValue(), typedParameterValue.getType() );
		}
		else if ( value instanceof Collection && !isRegisteredAsBasicType( value.getClass() ) ) {
			setParameterList( Integer.toString( position ), (Collection) value );
		}
		else {
			queryParameterBindings.getBinding( position ).setBindValue( value );
		}
		return this;
	}
