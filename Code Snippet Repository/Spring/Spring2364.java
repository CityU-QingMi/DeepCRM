	public static void registerConverters(ConverterRegistry registry) {
		DateFormatterRegistrar.addDateConverters(registry);

		registry.addConverter(new LocalDateTimeToLocalDateConverter());
		registry.addConverter(new LocalDateTimeToLocalTimeConverter());
		registry.addConverter(new ZonedDateTimeToLocalDateConverter());
		registry.addConverter(new ZonedDateTimeToLocalTimeConverter());
		registry.addConverter(new ZonedDateTimeToLocalDateTimeConverter());
		registry.addConverter(new ZonedDateTimeToOffsetDateTimeConverter());
		registry.addConverter(new ZonedDateTimeToInstantConverter());
		registry.addConverter(new OffsetDateTimeToLocalDateConverter());
		registry.addConverter(new OffsetDateTimeToLocalTimeConverter());
		registry.addConverter(new OffsetDateTimeToLocalDateTimeConverter());
		registry.addConverter(new OffsetDateTimeToZonedDateTimeConverter());
		registry.addConverter(new OffsetDateTimeToInstantConverter());
		registry.addConverter(new CalendarToZonedDateTimeConverter());
		registry.addConverter(new CalendarToOffsetDateTimeConverter());
		registry.addConverter(new CalendarToLocalDateConverter());
		registry.addConverter(new CalendarToLocalTimeConverter());
		registry.addConverter(new CalendarToLocalDateTimeConverter());
		registry.addConverter(new CalendarToInstantConverter());
		registry.addConverter(new LongToInstantConverter());
		registry.addConverter(new InstantToLongConverter());
	}
