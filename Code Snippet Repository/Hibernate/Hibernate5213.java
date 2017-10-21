	@Override
	public <X> Instant wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}

		if ( Instant.class.isInstance( value ) ) {
			return (Instant) value;
		}

		if ( Timestamp.class.isInstance( value ) ) {
			final Timestamp ts = (Timestamp) value;
			return ts.toInstant();
		}

		if ( Long.class.isInstance( value ) ) {
			return Instant.ofEpochMilli( (Long) value );
		}

		if ( Calendar.class.isInstance( value ) ) {
			final Calendar calendar = (Calendar) value;
			return ZonedDateTime.ofInstant( calendar.toInstant(), calendar.getTimeZone().toZoneId() ).toInstant();
		}

		if ( java.util.Date.class.isInstance( value ) ) {
			return ( (java.util.Date) value ).toInstant();
		}

		throw unknownWrap( value.getClass() );
	}
