	@Test
	public void defaultDomain() throws Exception {
		MBeanServerFactoryBean bean = new MBeanServerFactoryBean();
		bean.setDefaultDomain("foo");
		bean.afterPropertiesSet();
		try {
			MBeanServer server = bean.getObject();
			assertEquals("The default domain should be foo", "foo", server.getDefaultDomain());
		}
		finally {
			bean.destroy();
		}
	}
