	@Test
	public void testAutodetectWithExclude() throws Exception {
		ConfigurableApplicationContext ctx = load("autodetectMBeans.xml");
		try {
			ctx.getBean("exporter");
			MBeanServer server = ctx.getBean("server", MBeanServer.class);
			ObjectInstance instance = server.getObjectInstance(ObjectNameManager.getInstance("spring:mbean=true"));
			assertNotNull(instance);

			thrown.expect(InstanceNotFoundException.class);
			server.getObjectInstance(ObjectNameManager.getInstance("spring:mbean=false"));
		}
		finally {
			ctx.close();
		}
	}
