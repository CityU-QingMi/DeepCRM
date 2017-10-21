	@Test
	public void testMBeanIsUnregisteredForRuntimeExceptionDuringInitialization() throws Exception {
		BeanDefinitionBuilder builder1 = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
		BeanDefinitionBuilder builder2 = BeanDefinitionBuilder
				.rootBeanDefinition(RuntimeExceptionThrowingConstructorBean.class);

		String objectName1 = "spring:test=bean1";
		String objectName2 = "spring:test=bean2";

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition(objectName1, builder1.getBeanDefinition());
		factory.registerBeanDefinition(objectName2, builder2.getBeanDefinition());

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		Map<String, Object> beansToExport = new HashMap<>();
		beansToExport.put(objectName1, objectName1);
		beansToExport.put(objectName2, objectName2);
		exporter.setBeans(beansToExport);
		exporter.setBeanFactory(factory);

		try {
			start(exporter);
			fail("Must have failed during creation of RuntimeExceptionThrowingConstructorBean");
		}
		catch (RuntimeException expected) {
		}

		assertIsNotRegistered("Must have unregistered all previously registered MBeans due to RuntimeException",
				ObjectNameManager.getInstance(objectName1));
		assertIsNotRegistered("Must have never registered this MBean due to RuntimeException",
				ObjectNameManager.getInstance(objectName2));
	}
