	@Test
	@SuppressWarnings("")
	public void formatFieldForAnnotationWithPlaceholders() throws Exception {
		GenericApplicationContext context = new GenericApplicationContext();
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.setProperty("dateStyle", "S-");
		props.setProperty("datePattern", "M-d-yy");
		ppc.setProperties(props);
		context.getBeanFactory().registerSingleton("ppc", ppc);
		context.refresh();
		context.getBeanFactory().initializeBean(formattingService, "formattingService");
		formattingService.addFormatterForFieldAnnotation(new JodaDateTimeFormatAnnotationFormatterFactory());
		doTestFormatFieldForAnnotation(ModelWithPlaceholders.class, false);
	}
