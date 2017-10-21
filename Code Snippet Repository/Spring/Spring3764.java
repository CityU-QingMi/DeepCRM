	@Test
	public void withEmptyAgentIdAndFallbackToPlatformServer() {
		MBeanServerFactoryBean bean = new MBeanServerFactoryBean();
		bean.setAgentId("");
		bean.afterPropertiesSet();
		try {
			assertSame(ManagementFactory.getPlatformMBeanServer(), bean.getObject());
		}
		finally {
			bean.destroy();
		}
	}
