	@SuppressWarnings({ "", "" })
	@Test
	public void testRegisterNotificationListenerWithObjectNameBeforeBeanNameMappedToSameBeanInstance() throws Exception {
		String beanName = "testBean";
		ObjectName objectName = ObjectName.getInstance("spring:name=Test");

		SelfNamingTestBean testBean = new SelfNamingTestBean();
		testBean.setObjectName(objectName);

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerSingleton(beanName, testBean);

		Map<String, Object> beans = new HashMap<>();
		beans.put(beanName, testBean);

		Map listenerMappings = new HashMap();
		CountingAttributeChangeNotificationListener listener = new CountingAttributeChangeNotificationListener();
		listenerMappings.put(objectName, listener);
		listenerMappings.put(beanName, listener);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		exporter.setNotificationListenerMappings(listenerMappings);
		exporter.setBeanFactory(factory);
		start(exporter);
		assertIsRegistered("Should have registered MBean", objectName);

		server.setAttribute(objectName, new Attribute("Age", new Integer(77)));
		assertEquals("Listener should have been notified exactly once", 1, listener.getCount("Age"));
	}
