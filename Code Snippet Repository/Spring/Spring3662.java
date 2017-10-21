	@Test
	@SuppressWarnings("")
	public void formatFieldForAnnotationWithPlaceholdersAndFactoryBean() throws Exception {
		GenericApplicationContext context = new GenericApplicationContext();
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Properties props = new Properties();
		props.setProperty("dateStyle", "S-");
		props.setProperty("datePattern", "M-d-yy");
		ppc.setProperties(props);
		context.registerBeanDefinition("formattingService", new RootBeanDefinition(FormattingConversionServiceFactoryBean.class));
		context.getBeanFactory().registerSingleton("ppc", ppc);
		context.refresh();
		formattingService = context.getBean("formattingService", FormattingConversionService.class);
		doTestFormatFieldForAnnotation(ModelWithPlaceholders.class, false);
	}
