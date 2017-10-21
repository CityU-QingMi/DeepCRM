	@Override
	protected ConfigurableApplicationContext createContext() throws Exception {
		StaticApplicationContext parent = new StaticApplicationContext();

		Map<String, String> m = new HashMap<>();
		m.put("name", "Roderick");
		parent.registerPrototype("rod", org.springframework.tests.sample.beans.TestBean.class, new MutablePropertyValues(m));
		m.put("name", "Albert");
		parent.registerPrototype("father", org.springframework.tests.sample.beans.TestBean.class, new MutablePropertyValues(m));

		parent.refresh();
		parent.addApplicationListener(parentListener);

		this.sac = new StaticApplicationContext(parent);

		sac.registerSingleton("beanThatListens", BeanThatListens.class, new MutablePropertyValues());

		sac.registerSingleton("aca", ACATester.class, new MutablePropertyValues());

		sac.registerPrototype("aca-prototype", ACATester.class, new MutablePropertyValues());

		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(sac.getDefaultListableBeanFactory());
		reader.loadBeanDefinitions(new ClassPathResource("testBeans.properties", getClass()));
		sac.refresh();
		sac.addApplicationListener(listener);

		StaticMessageSource messageSource = sac.getStaticMessageSource();
		Map<String, String> usMessages = new HashMap<>(3);
		usMessages.put("message.format.example1", MSG_TXT1_US);
		usMessages.put("message.format.example2", MSG_TXT2_US);
		usMessages.put("message.format.example3", MSG_TXT3_US);
		messageSource.addMessages(usMessages, Locale.US);
		messageSource.addMessage("message.format.example1", Locale.UK, MSG_TXT1_UK);

		return sac;
	}
