    @SuppressWarnings({""})
    public <X> X unwrap(Array value, Class<X> type, WrapperOptions options) {
        if ( value == null ) {
            return null;
        }
        if ( Array.class.isAssignableFrom( type ) ) {
            return (X) value;
        }
        if ( String.class.isAssignableFrom( type ) ) {
            return (X) toString( value);
        }
        throw unknownUnwrap( type );
    }
