	@Deprecated
	@SuppressWarnings("")
	private <T> QueryParameter<T> resolveQueryParameter(String name) {
		for ( QueryParameter queryParameter : parameterListBindingMap.keySet() ) {
			if ( name.equals( queryParameter.getName() ) ) {
				return queryParameter;
			}
		}

		for ( QueryParameter queryParameter : parameterBindingMap.keySet() ) {
			if ( name.equals( queryParameter.getName() ) ) {
				return queryParameter;
			}
		}

		throw new IllegalArgumentException(
				"Unable to resolve given parameter name [" + name + "] to QueryParameter reference"
		);
	}
