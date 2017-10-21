	@SuppressWarnings({ "" })
	public static <T> T convert(Object value, Class<T> targetType) {
		if ( value == null ) {
			return null;
		}
		if ( targetType.equals( value.getClass() ) ) {
			return (T) value;
		}

		ValueHandler<T> valueHandler = determineAppropriateHandler( targetType );
		if ( valueHandler == null ) {
			throw unknownConversion( value, targetType );
		}
		return valueHandler.convert( value );
	}
