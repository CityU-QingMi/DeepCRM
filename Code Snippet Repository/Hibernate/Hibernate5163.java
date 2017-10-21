	public <X> BigDecimal wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( BigDecimal.class.isInstance( value ) ) {
			return (BigDecimal) value;
		}
		if ( BigInteger.class.isInstance( value ) ) {
			return new BigDecimal( (BigInteger) value );
		}
		if ( Number.class.isInstance( value ) ) {
			return BigDecimal.valueOf( ( (Number) value ).doubleValue() );
		}
		throw unknownWrap( value.getClass() );
	}
