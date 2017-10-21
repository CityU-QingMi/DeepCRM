	public BasicType resolveTimestampTemporalTypeVariant(Class javaType, Type baseType) {
		// prefer to use any Type already known - interprets TIMESTAMP as "no narrowing"
		if ( baseType != null && baseType instanceof BasicType ) {
			return (BasicType) baseType;
		}

		if ( Calendar.class.isAssignableFrom( javaType ) ) {
			return CalendarType.INSTANCE;
		}

		if ( java.util.Date.class.isAssignableFrom( javaType ) ) {
			return TimestampType.INSTANCE;
		}

		if ( Instant.class.isAssignableFrom( javaType ) ) {
			return InstantType.INSTANCE;
		}

		if ( OffsetDateTime.class.isAssignableFrom( javaType ) ) {
			return OffsetDateTimeType.INSTANCE;
		}

		if ( ZonedDateTime.class.isAssignableFrom( javaType ) ) {
			return ZonedDateTimeType.INSTANCE;
		}

		if ( OffsetTime.class.isAssignableFrom( javaType ) ) {
			return OffsetTimeType.INSTANCE;
		}

		throw new IllegalArgumentException( "Unsure how to handle given Java type [" + javaType.getName() + "] as TemporalType#TIMESTAMP" );
	}
