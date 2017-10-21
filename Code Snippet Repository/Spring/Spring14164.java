	@Before
	public void setup() {
		this.serverContainer = mock(ServerContainer.class);

		this.servletContext = new MockServletContext();
		this.servletContext.setAttribute("javax.websocket.server.ServerContainer", this.serverContainer);

		this.webAppContext = new AnnotationConfigWebApplicationContext();
		this.webAppContext.register(Config.class);
		this.webAppContext.setServletContext(this.servletContext);
		this.webAppContext.refresh();

		this.exporter = new ServerEndpointExporter();
	}
