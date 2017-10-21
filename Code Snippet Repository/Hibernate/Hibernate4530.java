	@Deprecated
	private QueryParameterListBinding locateQueryParameterListBinding(QueryParameter queryParameter) {
		QueryParameterListBinding result = parameterListBindingMap.get( queryParameter );

		if ( result == null && queryParameter.getName() != null ) {
			for ( Map.Entry<QueryParameter, QueryParameterListBinding> entry : parameterListBindingMap.entrySet() ) {
				if ( queryParameter.getName().equals( entry.getKey().getName() ) ) {
					result = entry.getValue();
					break;
				}
			}
		}

		return result;
	}
