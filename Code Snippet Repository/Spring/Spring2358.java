	@Override
	public Parser<?> getParser(DateTimeFormat annotation, Class<?> fieldType) {
		if (LocalDate.class == fieldType) {
			return new LocalDateParser(getFormatter(annotation, fieldType));
		}
		else if (LocalTime.class == fieldType) {
			return new LocalTimeParser(getFormatter(annotation, fieldType));
		}
		else if (LocalDateTime.class == fieldType) {
			return new LocalDateTimeParser(getFormatter(annotation, fieldType));
		}
		else {
			return new DateTimeParser(getFormatter(annotation, fieldType));
		}
	}
