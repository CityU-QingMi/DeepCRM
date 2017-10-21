	@Test
	public void testSelfNaming() throws Exception {
		ObjectName objectName = ObjectNameManager.getInstance(OBJECT_NAME);
		SelfNamingTestBean testBean = new SelfNamingTestBean();
		testBean.setObjectName(objectName);

		Map<String, Object> beans = new HashMap<>();
		beans.put("foo", testBean);

		MBeanExporter exporter = new MBeanExporter();
		exporter.setServer(server);
		exporter.setBeans(beans);

		start(exporter);

		ObjectInstance instance = server.getObjectInstance(objectName);
		assertNotNull(instance);
	}
