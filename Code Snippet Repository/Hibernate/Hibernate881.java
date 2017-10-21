	public static Map<String, String> extractParameters(List<JaxbHbmConfigParameterType> xmlParamElements) {
		if ( xmlParamElements == null || xmlParamElements.isEmpty() ) {
			return Collections.emptyMap();
		}
		final HashMap<String,String> params = new HashMap<String, String>();
		for ( JaxbHbmConfigParameterType paramElement : xmlParamElements ) {
			params.put( paramElement.getName(), paramElement.getValue() );
		}
		return params;
	}
