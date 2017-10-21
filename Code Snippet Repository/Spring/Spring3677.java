	@Test
	public void testRegisterManagedResourceWithGeneratedObjectNameWithoutUniqueness() throws Exception {
		final ObjectName objectNameTemplate = ObjectNameManager.getInstance("spring:type=Test");

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		exporter.setEnsureUniqueRuntimeObjectNames(false);
		exporter.setNamingStrategy(new ObjectNamingStrategy() {
			@Override
			public ObjectName getObjectName(Object managedBean, String beanKey) {
				return objectNameTemplate;
			}
		});

		JmxTestBean bean1 = new JmxTestBean();
		JmxTestBean bean2 = new JmxTestBean();

		ObjectName reg1 = exporter.registerManagedResource(bean1);
		assertIsRegistered("Bean 1 not registered with MBeanServer", reg1);

		try {
			exporter.registerManagedResource(bean2);
			fail("Shouldn't be able to register a runtime MBean with a reused ObjectName.");
		}
		catch (MBeanExportException e) {
			assertEquals("Incorrect root cause", InstanceAlreadyExistsException.class, e.getCause().getClass());
		}
	}
