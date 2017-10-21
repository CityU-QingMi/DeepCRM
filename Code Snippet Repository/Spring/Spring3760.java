	@Test
	public void getObject() throws Exception {
		MBeanServerFactoryBean bean = new MBeanServerFactoryBean();
		bean.afterPropertiesSet();
		try {
			MBeanServer server = bean.getObject();
			assertNotNull("The MBeanServer should not be null", server);
		}
		finally {
			bean.destroy();
		}
	}
