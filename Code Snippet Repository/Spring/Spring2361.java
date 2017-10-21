	@SuppressWarnings("")
	public static void registerConverters(ConverterRegistry registry) {
		DateFormatterRegistrar.addDateConverters(registry);

		registry.addConverter(new DateTimeToLocalDateConverter());
		registry.addConverter(new DateTimeToLocalTimeConverter());
		registry.addConverter(new DateTimeToLocalDateTimeConverter());
		registry.addConverter(new DateTimeToDateMidnightConverter());
		registry.addConverter(new DateTimeToMutableDateTimeConverter());
		registry.addConverter(new DateTimeToInstantConverter());
		registry.addConverter(new DateTimeToDateConverter());
		registry.addConverter(new DateTimeToCalendarConverter());
		registry.addConverter(new DateTimeToLongConverter());
		registry.addConverter(new DateToReadableInstantConverter());
		registry.addConverter(new CalendarToReadableInstantConverter());
		registry.addConverter(new LongToReadableInstantConverter());
		registry.addConverter(new LocalDateTimeToLocalDateConverter());
		registry.addConverter(new LocalDateTimeToLocalTimeConverter());
	}
