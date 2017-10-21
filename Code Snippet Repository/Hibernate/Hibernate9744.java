	private static void validateArgs(Object bean, String propertyName) {
		if ( bean == null ) {
			throw new IllegalArgumentException( "bean is null" );
		}
		if ( propertyName == null ) {
			throw new IllegalArgumentException( "propertyName is null" );
		}
		if ( propertyName.trim().length() == 0 ) {
			throw new IllegalArgumentException( "propertyName is empty" );
		}
	}
