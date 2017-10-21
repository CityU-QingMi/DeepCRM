	@Override
	public <X> Date wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Date.class.isInstance( value ) ) {
			return (Date) value;
		}

		if ( Long.class.isInstance( value ) ) {
			return new java.sql.Date( ( (Long) value ).longValue() );
		}

		if ( Calendar.class.isInstance( value ) ) {
			return new java.sql.Date( ( (Calendar) value ).getTimeInMillis() );
		}

		if ( java.util.Date.class.isInstance( value ) ) {
			return new java.sql.Date( ( (java.util.Date) value ).getTime() );
		}

		throw unknownWrap( value.getClass() );
	}
