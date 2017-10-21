	@Test
	public void withLocateExistingAndFallbackToPlatformServer() {
		MBeanServerFactoryBean bean = new MBeanServerFactoryBean();
		bean.setLocateExistingServerIfPossible(true);
		bean.afterPropertiesSet();
		try {
			assertSame(ManagementFactory.getPlatformMBeanServer(), bean.getObject());
		}
		finally {
			bean.destroy();
		}
	}
