	@Test
	public void testRegisterReplaceExisting() throws Exception {
		ObjectName objectName = ObjectNameManager.getInstance(OBJECT_NAME);

		Person preRegistered = new Person();
		preRegistered.setName("Rob Harrop");

		server.registerMBean(preRegistered, objectName);

		Person springRegistered = new Person();
		springRegistered.setName("Sally Greenwood");

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName.toString(), springRegistered);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);
		exporter.setRegistrationPolicy(RegistrationPolicy.REPLACE_EXISTING);

		start(exporter);

		ObjectInstance instance = server.getObjectInstance(objectName);
		assertNotNull(instance);

		// should still be the new bean with name Sally Greenwood
		assertEquals("Sally Greenwood", server.getAttribute(objectName, "Name"));
	}
