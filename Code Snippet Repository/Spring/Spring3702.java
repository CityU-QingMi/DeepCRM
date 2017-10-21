	@Test
	public void testAutodetectNoMBeans() throws Exception {
		ConfigurableApplicationContext ctx = load("autodetectNoMBeans.xml");
		try {
			ctx.getBean("exporter");
		}
		finally {
			ctx.close();
		}
	}
