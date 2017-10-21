	@Test
	public void testRegisterIgnoreExisting() throws Exception {
		ObjectName objectName = ObjectNameManager.getInstance(OBJECT_NAME);

		Person preRegistered = new Person();
		preRegistered.setName("Rob Harrop");

		server.registerMBean(preRegistered, objectName);

		Person springRegistered = new Person();
		springRegistered.setName("Sally Greenwood");

		String objectName2 = "spring:test=equalBean";

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName.toString(), springRegistered);
		beans.put(objectName2, springRegistered);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		exporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);

		start(exporter);

		ObjectInstance instance = server.getObjectInstance(objectName);
		assertNotNull(instance);
		ObjectInstance instance2 = server.getObjectInstance(new ObjectName(objectName2));
		assertNotNull(instance2);

		// should still be the first bean with name Rob Harrop
		assertEquals("Rob Harrop", server.getAttribute(objectName, "Name"));
	}
