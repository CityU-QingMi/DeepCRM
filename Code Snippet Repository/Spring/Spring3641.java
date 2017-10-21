	@Before
	public void setUp() {
		DefaultConversionService.addDefaultConverters(conversionService);
		conversionService.setEmbeddedValueResolver(new StringValueResolver() {
			@Override
			public String resolveStringValue(String strVal) {
				if ("${pattern}".equals(strVal)) {
					return "#,##.00";
				}
				else {
					return strVal;
				}
			}
		});
		conversionService.addFormatterForFieldType(Number.class, new NumberStyleFormatter());
		conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());
		LocaleContextHolder.setLocale(Locale.US);
		binder = new DataBinder(new TestBean());
		binder.setConversionService(conversionService);
	}
