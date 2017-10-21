	@Override
	protected WebApplicationContext initWebAppContext() {
		ServletContext servletContext = this.webAppContext.getServletContext();
		Assert.state(servletContext != null, "No ServletContext");
		ApplicationContext rootWac = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		if (rootWac == null) {
			rootWac = this.webAppContext;
			ApplicationContext parent = this.webAppContext.getParent();
			while (parent != null) {
				if (parent instanceof WebApplicationContext && !(parent.getParent() instanceof WebApplicationContext)) {
					rootWac = parent;
					break;
				}
				parent = parent.getParent();
			}
			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, rootWac);
		}

		return this.webAppContext;
	}
