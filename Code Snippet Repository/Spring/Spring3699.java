	@Test
	public void testAutodetectMBeans() throws Exception {
		ConfigurableApplicationContext ctx = load("autodetectMBeans.xml");
		try {
			ctx.getBean("exporter");
			MBeanServer server = ctx.getBean("server", MBeanServer.class);
			ObjectInstance instance = server.getObjectInstance(ObjectNameManager.getInstance("spring:mbean=true"));
			assertNotNull(instance);
			instance = server.getObjectInstance(ObjectNameManager.getInstance("spring:mbean2=true"));
			assertNotNull(instance);
			instance = server.getObjectInstance(ObjectNameManager.getInstance("spring:mbean3=true"));
			assertNotNull(instance);
		}
		finally {
			ctx.close();
		}
	}
