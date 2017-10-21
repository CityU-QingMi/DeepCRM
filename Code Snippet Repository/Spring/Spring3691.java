	@Test
	public void testBeanNameCanBeUsedInNotificationListenersMap() throws Exception {
		String beanName = "charlesDexterWard";
		BeanDefinitionBuilder testBean = BeanDefinitionBuilder.rootBeanDefinition(JmxTestBean.class);

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition(beanName, testBean.getBeanDefinition());
		factory.preInstantiateSingletons();
		Object testBeanInstance = factory.getBean(beanName);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		Map<String, Object> beansToExport = new HashMap<>();
		beansToExport.put("test:what=ever", testBeanInstance);
		exporter.setBeans(beansToExport);
		exporter.setBeanFactory(factory);
		StubNotificationListener listener = new StubNotificationListener();
		exporter.setNotificationListenerMappings(Collections.singletonMap(beanName, listener));

		start(exporter);
	}
