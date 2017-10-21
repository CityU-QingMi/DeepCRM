	@Override
	protected void initServer() throws Exception {
		this.tomcatServer = new Tomcat();
		this.tomcatServer.setBaseDir(baseDir);
		this.tomcatServer.setHostname(getHost());
		this.tomcatServer.setPort(getPort());

		ServletHttpHandlerAdapter servlet = initServletAdapter();

		File base = new File(System.getProperty("java.io.tmpdir"));
		Context rootContext = tomcatServer.addContext("", base.getAbsolutePath());
		Tomcat.addServlet(rootContext, "httpHandlerServlet", servlet);
		rootContext.addServletMappingDecoded("/", "httpHandlerServlet");
		if (wsListener != null) {
			rootContext.addApplicationListener(wsListener.getName());
		}
	}
