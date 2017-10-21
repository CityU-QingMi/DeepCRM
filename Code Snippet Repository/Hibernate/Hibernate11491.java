	@After
	public void releaseCachSupport() throws Exception {
		testSupport.tearDown();

		if (preferIPv4Stack == null) {
			System.clearProperty(PREFER_IPV4STACK);
		} else {
			System.setProperty(PREFER_IPV4STACK, preferIPv4Stack);
		}

		if (jgroupsCfgFile == null)
			System.clearProperty(JGROUPS_CFG_FILE);
		else
			System.setProperty(JGROUPS_CFG_FILE, jgroupsCfgFile);
	}
