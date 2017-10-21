	@Override
	public <X> Long wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Long.class.isInstance( value ) ) {
			return (Long) value;
		}
		if ( Number.class.isInstance( value ) ) {
			return ( (Number) value ).longValue();
		}
		else if ( String.class.isInstance( value ) ) {
			return Long.valueOf( ( (String) value ) );
		}
		throw unknownWrap( value.getClass() );
	}
