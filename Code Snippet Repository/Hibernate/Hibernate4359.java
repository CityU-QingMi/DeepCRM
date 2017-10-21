	@SuppressWarnings({ "" })
	public static <T> ValueHandler<T> determineAppropriateHandler(Class<T> targetType) {
		if ( String.class.equals( targetType ) ) {
			return (ValueHandler<T>) StringValueHandler.INSTANCE;
		}
		if ( Byte.class.equals( targetType ) || Byte.TYPE.equals( targetType ) ) {
			return (ValueHandler<T>) ByteValueHandler.INSTANCE;
		}
		if ( Short.class.equals( targetType ) || Short.TYPE.equals( targetType ) ) {
			return (ValueHandler<T>) ShortValueHandler.INSTANCE;
		}
		if ( Integer.class.equals( targetType ) || Integer.TYPE.equals( targetType ) ) {
			return (ValueHandler<T>) IntegerValueHandler.INSTANCE;
		}
		if ( Long.class.equals( targetType ) || Long.TYPE.equals( targetType ) ) {
			return (ValueHandler<T>) LongValueHandler.INSTANCE;
		}
		if ( Float.class.equals( targetType ) || Float.TYPE.equals( targetType ) ) {
			return (ValueHandler<T>) FloatValueHandler.INSTANCE;
		}
		if ( Double.class.equals( targetType ) || Double.TYPE.equals( targetType ) ) {
			return (ValueHandler<T>) DoubleValueHandler.INSTANCE;
		}
		if ( BigInteger.class.equals( targetType ) ) {
			return (ValueHandler<T>) BigIntegerValueHandler.INSTANCE;
		}
		if ( BigDecimal.class.equals( targetType ) ) {
			return (ValueHandler<T>) BigDecimalValueHandler.INSTANCE;
		}
		if ( Boolean.class.equals( targetType ) ) {
			return (ValueHandler<T>) BooleanValueHandler.INSTANCE;
		}
		return null;
	}
