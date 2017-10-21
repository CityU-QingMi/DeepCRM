	@Test
	public void testWithExposeClassLoader() throws Exception {
		String name = "Rob Harrop";
		String otherName = "Juergen Hoeller";

		JmxTestBean bean = new JmxTestBean();
		bean.setName(name);
		ObjectName objectName = ObjectNameManager.getInstance("spring:type=Test");

		Map<String, Object> beans = new HashMap<>();
		beans.put(objectName.toString(), bean);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(getServer());
		exporter.setBeans(beans);
		exporter.setExposeManagedResourceClassLoader(true);
		start(exporter);

		assertIsRegistered("Bean instance not registered", objectName);

		Object result = server.invoke(objectName, "add", new Object[] {new Integer(2), new Integer(3)}, new String[] {
				int.class.getName(), int.class.getName()});

		assertEquals("Incorrect result return from add", result, new Integer(5));
		assertEquals("Incorrect attribute value", name, server.getAttribute(objectName, "Name"));

		server.setAttribute(objectName, new Attribute("Name", otherName));
		assertEquals("Incorrect updated name.", otherName, bean.getName());
	}
