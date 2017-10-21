	@Test
	public void formatFieldForTypeWithPrinterParserWithCoercion() throws ParseException {
		formattingService.addConverter(new Converter<DateTime, LocalDate>() {
			@Override
			public LocalDate convert(DateTime source) {
				return source.toLocalDate();
			}
		});
		formattingService.addFormatterForFieldType(LocalDate.class, new ReadablePartialPrinter(DateTimeFormat
				.shortDate()), new DateTimeParser(DateTimeFormat.shortDate()));
		String formatted = formattingService.convert(new LocalDate(2009, 10, 31), String.class);
		assertEquals("10/31/09", formatted);
		LocalDate date = formattingService.convert("10/31/09", LocalDate.class);
		assertEquals(new LocalDate(2009, 10, 31), date);
	}
