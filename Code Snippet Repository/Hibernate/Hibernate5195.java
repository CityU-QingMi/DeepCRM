	public <X> Class wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Class.class.isInstance( value ) ) {
			return (Class) value;
		}
		if ( String.class.isInstance( value ) ) {
			return fromString( (String)value );
		}
		throw unknownWrap( value.getClass() );
	}
