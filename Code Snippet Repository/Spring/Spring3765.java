	private void testCreation(boolean referenceShouldExist, String failMsg) throws Exception {
		MBeanServerFactoryBean bean = new MBeanServerFactoryBean();
		bean.setRegisterWithFactory(referenceShouldExist);
		bean.afterPropertiesSet();

		try {
			MBeanServer server = bean.getObject();
			List<MBeanServer> servers = MBeanServerFactory.findMBeanServer(null);

			boolean found = false;
			for (MBeanServer candidate : servers) {
				if (candidate == server) {
					found = true;
					break;
				}
			}

			if (!(found == referenceShouldExist)) {
				fail(failMsg);
			}
		}
		finally {
			bean.destroy();
		}
	}
