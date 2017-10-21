	@Test
	public void lazyNaming() throws Exception {
		ConfigurableApplicationContext ctx =
				new ClassPathXmlApplicationContext("org/springframework/jmx/export/annotation/lazyNaming.xml");
		try {
			MBeanServer server = (MBeanServer) ctx.getBean("server");
			ObjectName oname = ObjectNameManager.getInstance("bean:name=testBean4");
			assertNotNull(server.getObjectInstance(oname));
			String name = (String) server.getAttribute(oname, "Name");
			assertEquals("Invalid name returned", "TEST", name);
		}
		finally {
			ctx.close();
		}
	}
