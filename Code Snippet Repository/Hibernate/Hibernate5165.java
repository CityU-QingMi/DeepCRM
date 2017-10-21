	@Override
	public <X> BigInteger wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( BigInteger.class.isInstance( value ) ) {
			return (BigInteger) value;
		}
		if ( BigDecimal.class.isInstance( value ) ) {
			return ( (BigDecimal) value ).toBigIntegerExact();
		}
		if ( Number.class.isInstance( value ) ) {
			return BigInteger.valueOf( ( (Number) value ).longValue() );
		}
		throw unknownWrap( value.getClass() );
	}
