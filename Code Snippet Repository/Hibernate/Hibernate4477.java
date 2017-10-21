	protected Type determineType(String namedParam, Class retType) {
		Type type = queryParameterBindings.getBinding( namedParam ).getBindType();
		if ( type == null ) {
			type = getParameterMetadata().getQueryParameter( namedParam ).getType();
		}
		if ( type == null ) {
			type = getProducer().getFactory().resolveParameterBindType( retType );
		}
		return type;
	}
