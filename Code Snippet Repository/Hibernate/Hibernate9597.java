    public <X> Array wrap(X value, WrapperOptions options) {
        if ( value == null ) {
            return null;
        }
        if ( String.class.isInstance( value ) ) {
            return fromString( (String) value );
        }
        if ( Array.class.isInstance( value ) ) {
            return (Array) value;
        }
        throw unknownWrap( value.getClass() );
    }
