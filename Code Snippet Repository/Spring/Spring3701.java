	@Test
	public void testAutodetectLazyMBeans() throws Exception {
		ConfigurableApplicationContext ctx = load("autodetectLazyMBeans.xml");
		try {
			ctx.getBean("exporter");
			MBeanServer server = ctx.getBean("server", MBeanServer.class);

			ObjectName oname = ObjectNameManager.getInstance("spring:mbean=true");
			assertNotNull(server.getObjectInstance(oname));
			String name = (String) server.getAttribute(oname, "Name");
			assertEquals("Invalid name returned", "Rob Harrop", name);

			oname = ObjectNameManager.getInstance("spring:mbean=another");
			assertNotNull(server.getObjectInstance(oname));
			name = (String) server.getAttribute(oname, "Name");
			assertEquals("Invalid name returned", "Juergen Hoeller", name);
		}
		finally {
			ctx.close();
		}
	}
