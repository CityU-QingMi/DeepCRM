	@Test
	public void testLazyAssembling() throws Exception {
		System.setProperty("domain", "bean");
		load(LazyAssemblingConfiguration.class);
		try {
			MBeanServer server = (MBeanServer) this.ctx.getBean("server");

			validateMBeanAttribute(server, "bean:name=testBean4", "TEST");
			validateMBeanAttribute(server, "bean:name=testBean5", "FACTORY");
			validateMBeanAttribute(server, "spring:mbean=true", "Rob Harrop");
			validateMBeanAttribute(server, "spring:mbean=another", "Juergen Hoeller");
		}
		finally {
			System.clearProperty("domain");
		}
	}
