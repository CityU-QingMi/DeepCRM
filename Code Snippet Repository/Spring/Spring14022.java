	@Override
	public void setup() {
		Connector connector = new Connector(Http11NioProtocol.class.getName());
		connector.setPort(0);

		File baseDir = createTempDir("tomcat");
		String baseDirPath = baseDir.getAbsolutePath();

		this.tomcatServer = new Tomcat();
		this.tomcatServer.setBaseDir(baseDirPath);
		this.tomcatServer.setPort(0);
		this.tomcatServer.getService().addConnector(connector);
		this.tomcatServer.setConnector(connector);
	}
