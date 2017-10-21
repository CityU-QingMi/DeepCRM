	@SuppressWarnings({ "", "" })
	@Test
	public void testRegisterNotificationListenerWithTwoBeanNamesMappedToDifferentBeanInstances() throws Exception {
		String beanName1 = "testBean1";
		String beanName2 = "testBean2";

		ObjectName objectName1 = ObjectName.getInstance("spring:name=Test1");
		ObjectName objectName2 = ObjectName.getInstance("spring:name=Test2");

		SelfNamingTestBean testBean1 = new SelfNamingTestBean();
		testBean1.setObjectName(objectName1);

		SelfNamingTestBean testBean2 = new SelfNamingTestBean();
		testBean2.setObjectName(objectName2);

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerSingleton(beanName1, testBean1);
		factory.registerSingleton(beanName2, testBean2);

		Map<String, Object> beans = new HashMap<>();
		beans.put(beanName1, testBean1);
		beans.put(beanName2, testBean2);

		Map listenerMappings = new HashMap();
		CountingAttributeChangeNotificationListener listener = new CountingAttributeChangeNotificationListener();
		listenerMappings.put(beanName1, listener);
		listenerMappings.put(beanName2, listener);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		exporter.setNotificationListenerMappings(listenerMappings);
		exporter.setBeanFactory(factory);
		start(exporter);
		assertIsRegistered("Should have registered MBean", objectName1);
		assertIsRegistered("Should have registered MBean", objectName2);

		server.setAttribute(ObjectNameManager.getInstance(objectName1), new Attribute("Age", new Integer(77)));
		assertEquals("Listener not notified for testBean1", 1, listener.getCount("Age"));

		server.setAttribute(ObjectNameManager.getInstance(objectName2), new Attribute("Age", new Integer(33)));
		assertEquals("Listener not notified for testBean2", 2, listener.getCount("Age"));
	}
