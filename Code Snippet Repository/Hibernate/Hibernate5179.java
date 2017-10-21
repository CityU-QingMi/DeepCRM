	@Override
	public <X> Byte wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Byte.class.isInstance( value ) ) {
			return (Byte) value;
		}
		if ( Number.class.isInstance( value ) ) {
			return ( (Number) value ).byteValue();
		}
		if ( String.class.isInstance( value ) ) {
			return Byte.valueOf( ( (String) value ) );
		}
		throw unknownWrap( value.getClass() );
	}
