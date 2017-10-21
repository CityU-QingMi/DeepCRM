	@Override
	public void registerFormatters(FormatterRegistry registry) {
		addDateConverters(registry);
		registry.addFormatterForFieldAnnotation(new DateTimeFormatAnnotationFormatterFactory());

		// In order to retain back compatibility we only register Date/Calendar
		// types when a user defined formatter is specified (see SPR-10105)
		if (this.dateFormatter != null) {
			registry.addFormatter(this.dateFormatter);
			registry.addFormatterForFieldType(Calendar.class, this.dateFormatter);
		}
	}
