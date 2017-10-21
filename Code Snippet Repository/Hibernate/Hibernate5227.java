	@Override
	public <X> Date wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Time.class.isInstance( value ) ) {
			return (Time) value;
		}

		if ( Long.class.isInstance( value ) ) {
			return new Time( (Long) value );
		}

		if ( Calendar.class.isInstance( value ) ) {
			return new Time( ( (Calendar) value ).getTimeInMillis() );
		}

		if ( Date.class.isInstance( value ) ) {
			return (Date) value;
		}

		throw unknownWrap( value.getClass() );
	}
