	private static Map<String, String> extractConfigParameters(List<JaxbHbmConfigParameterType> paramElementList) {
		if ( CollectionHelper.isEmpty( paramElementList ) ) {
			return Collections.emptyMap();
		}

		final Map<String,String> params = new HashMap<String,String>();
		for ( JaxbHbmConfigParameterType paramElement : paramElementList ) {
			params.put(
					paramElement.getName(),
					paramElement.getValue()
			);
		}
		return params;
	}
