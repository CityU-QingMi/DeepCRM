	@Override
	public <X> Date wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Timestamp.class.isInstance( value ) ) {
			return (Timestamp) value;
		}

		if ( Long.class.isInstance( value ) ) {
			return new Timestamp( (Long) value );
		}

		if ( Calendar.class.isInstance( value ) ) {
			return new Timestamp( ( (Calendar) value ).getTimeInMillis() );
		}

		if ( Date.class.isInstance( value ) ) {
			return (Date) value;
		}

		throw unknownWrap( value.getClass() );
	}
