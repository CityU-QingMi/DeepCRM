	public static FlushMode interpretFlushMode(Object value) {
		if ( value == null ) {
			return FlushMode.AUTO;
		}
		if ( FlushMode.class.isInstance( value ) ) {
			return (FlushMode) value;
		}
		else if ( FlushModeType.class.isInstance( value ) ) {
			return getFlushMode( (FlushModeType) value );
		}
		else if ( String.class.isInstance( value ) ) {
			return interpretExternalSetting( (String) value );
		}

		throw new IllegalArgumentException( "Unknown FlushMode source : " + value );

	}
