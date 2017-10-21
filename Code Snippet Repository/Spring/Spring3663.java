	@Test
	public void formatFieldForAnnotationWithSubclassAsFieldType() throws Exception {
		formattingService.addFormatterForFieldAnnotation(new JodaDateTimeFormatAnnotationFormatterFactory() {
			@Override
			public Printer<?> getPrinter(org.springframework.format.annotation.DateTimeFormat annotation, Class<?> fieldType) {
				assertEquals(MyDate.class, fieldType);
				return super.getPrinter(annotation, fieldType);
			}
		});
		formattingService.addConverter(new Converter<MyDate, Long>() {
			@Override
			public Long convert(MyDate source) {
				return source.getTime();
			}
		});
		formattingService.addConverter(new Converter<MyDate, Date>() {
			@Override
			public Date convert(MyDate source) {
				return source;
			}
		});

		formattingService.convert(new MyDate(), new TypeDescriptor(ModelWithSubclassField.class.getField("date")),
				TypeDescriptor.valueOf(String.class));
	}
