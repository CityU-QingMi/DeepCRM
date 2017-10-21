	@Override
	public void refresh() throws BeansException {
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("commandClass", "org.springframework.tests.sample.beans.TestBean");
		pvs.add("formView", "form");

		registerSingleton("/locale.do", LocaleChecker.class);

		addMessage("test", Locale.ENGLISH, "test message");
		addMessage("test", Locale.CANADA, "Canadian & test message");
		addMessage("testArgs", Locale.ENGLISH, "test {0} message {1}");
		addMessage("testArgsFormat", Locale.ENGLISH, "test {0} message {1,number,#.##} X");

		registerSingleton(UiApplicationContextUtils.THEME_SOURCE_BEAN_NAME, DummyThemeSource.class);

		registerSingleton("handlerMapping", BeanNameUrlHandlerMapping.class);
		registerSingleton("viewResolver", InternalResourceViewResolver.class);

		pvs = new MutablePropertyValues();
		pvs.add("location", "org/springframework/web/context/WEB-INF/sessionContext.xml");
		registerSingleton("viewResolver2", XmlViewResolver.class, pvs);

		super.refresh();
	}
