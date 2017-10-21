	public BasicType resolveTimeTemporalTypeVariant(Class javaType, Type baseType) {
		if ( Calendar.class.isAssignableFrom( javaType ) ) {
			return CalendarTimeType.INSTANCE;
		}

		if ( java.util.Date.class.isAssignableFrom( javaType ) ) {
			return TimestampType.INSTANCE;
		}

		throw new IllegalArgumentException( "Unsure how to handle given Java type [" + javaType.getName() + "] as TemporalType#TIME" );
	}
