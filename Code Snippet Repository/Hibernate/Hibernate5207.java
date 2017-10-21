	@Override
	public <X> Double wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Double.class.isInstance( value ) ) {
			return (Double) value;
		}
		if ( Number.class.isInstance( value ) ) {
			return ( (Number) value ).doubleValue();
		}
		else if ( String.class.isInstance( value ) ) {
			return Double.valueOf( ( (String) value ) );
		}
		throw unknownWrap( value.getClass() );
	}
