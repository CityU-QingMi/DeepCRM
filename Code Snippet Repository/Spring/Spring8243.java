	@Override
	public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
		if (context instanceof WebApplicationContext) {
			WebApplicationContext wac = (WebApplicationContext) context;
			ServletContext sc = wac.getServletContext();
			if (sc != null) {
				sc.setAttribute("javax.websocket.server.ServerContainer", new MockServerContainer());
			}
		}
	}
