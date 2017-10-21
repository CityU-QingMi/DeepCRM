	public JavaTypeDescriptorRegistry() {
		addDescriptorInternal( ByteTypeDescriptor.INSTANCE );
		addDescriptorInternal( BooleanTypeDescriptor.INSTANCE );
		addDescriptorInternal( CharacterTypeDescriptor.INSTANCE );
		addDescriptorInternal( ShortTypeDescriptor.INSTANCE );
		addDescriptorInternal( IntegerTypeDescriptor.INSTANCE );
		addDescriptorInternal( LongTypeDescriptor.INSTANCE );
		addDescriptorInternal( FloatTypeDescriptor.INSTANCE );
		addDescriptorInternal( DoubleTypeDescriptor.INSTANCE );
		addDescriptorInternal( BigDecimalTypeDescriptor.INSTANCE );
		addDescriptorInternal( BigIntegerTypeDescriptor.INSTANCE );

		addDescriptorInternal( StringTypeDescriptor.INSTANCE );

		addDescriptorInternal( BlobTypeDescriptor.INSTANCE );
		addDescriptorInternal( ClobTypeDescriptor.INSTANCE );
		addDescriptorInternal( NClobTypeDescriptor.INSTANCE );

		addDescriptorInternal( ByteArrayTypeDescriptor.INSTANCE );
		addDescriptorInternal( CharacterArrayTypeDescriptor.INSTANCE );
		addDescriptorInternal( PrimitiveByteArrayTypeDescriptor.INSTANCE );
		addDescriptorInternal( PrimitiveCharacterArrayTypeDescriptor.INSTANCE );

		addDescriptorInternal( DurationJavaDescriptor.INSTANCE );
		addDescriptorInternal( InstantJavaDescriptor.INSTANCE );
		addDescriptorInternal( LocalDateJavaDescriptor.INSTANCE );
		addDescriptorInternal( LocalDateTimeJavaDescriptor.INSTANCE );
		addDescriptorInternal( OffsetDateTimeJavaDescriptor.INSTANCE );
		addDescriptorInternal( OffsetTimeJavaDescriptor.INSTANCE );
		addDescriptorInternal( ZonedDateTimeJavaDescriptor.INSTANCE );

		addDescriptorInternal( CalendarTypeDescriptor.INSTANCE );
		addDescriptorInternal( DateTypeDescriptor.INSTANCE );
		descriptorsByClass.put( java.sql.Date.class, JdbcDateTypeDescriptor.INSTANCE );
		descriptorsByClass.put( java.sql.Time.class, JdbcTimeTypeDescriptor.INSTANCE );
		descriptorsByClass.put( java.sql.Timestamp.class, JdbcTimestampTypeDescriptor.INSTANCE );
		addDescriptorInternal( TimeZoneTypeDescriptor.INSTANCE );

		addDescriptorInternal( ClassTypeDescriptor.INSTANCE );

		addDescriptorInternal( CurrencyTypeDescriptor.INSTANCE );
		addDescriptorInternal( LocaleTypeDescriptor.INSTANCE );
		addDescriptorInternal( UrlTypeDescriptor.INSTANCE );
		addDescriptorInternal( UUIDTypeDescriptor.INSTANCE );
	}
