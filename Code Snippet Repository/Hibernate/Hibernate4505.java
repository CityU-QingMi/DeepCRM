	@SuppressWarnings("")
	public BasicType resolveDateTemporalTypeVariant(Class javaType, Type baseType) {
		// prefer to use any Type already known
		if ( baseType != null && baseType instanceof BasicType ) {
			if ( baseType.getReturnedClass().isAssignableFrom( javaType ) ) {
				return (BasicType) baseType;
			}
		}

		if ( Calendar.class.isAssignableFrom( javaType ) ) {
			return CalendarDateType.INSTANCE;
		}

		if ( java.util.Date.class.isAssignableFrom( javaType ) ) {
			return TimestampType.INSTANCE;
		}

		if ( Instant.class.isAssignableFrom( javaType ) ) {
			return OffsetDateTimeType.INSTANCE;
		}

		if ( OffsetDateTime.class.isAssignableFrom( javaType ) ) {
			return OffsetDateTimeType.INSTANCE;
		}

		if ( ZonedDateTime.class.isAssignableFrom( javaType ) ) {
			return ZonedDateTimeType.INSTANCE;
		}

		throw new IllegalArgumentException( "Unsure how to handle given Java type [" + javaType.getName() + "] as TemporalType#DATE" );
	}
