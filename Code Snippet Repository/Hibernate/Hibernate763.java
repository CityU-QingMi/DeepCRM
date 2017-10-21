	private Map<String, String> extractStrings(Properties properties) {
		final Map<String, String> parameters = new HashMap<String, String>();

		for ( Map.Entry entry : properties.entrySet() ) {
			if ( String.class.isInstance( entry.getKey() )
					&& String.class.isInstance( entry.getValue() ) ) {
				parameters.put(
						(String) entry.getKey(),
						(String) entry.getValue()
				);
			}
		}

		return parameters;
	}
