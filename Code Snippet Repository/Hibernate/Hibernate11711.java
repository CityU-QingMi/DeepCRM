	private Main startJndiServer() throws Exception {
		// Create an in-memory jndi
		NamingServer namingServer = new NamingServer();
		NamingContext.setLocal(namingServer);
		Main namingMain = new Main();
		namingMain.setInstallGlobalService(true);
		namingMain.setPort( -1 );
		namingMain.start();
		return namingMain;
	}
