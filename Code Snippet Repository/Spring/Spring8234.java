	protected void configureWebResources(GenericWebApplicationContext context,
			WebMergedContextConfiguration webMergedConfig) {

		ApplicationContext parent = context.getParent();

		// if the WAC has no parent or the parent is not a WAC, set the WAC as
		// the Root WAC:
		if (parent == null || (!(parent instanceof WebApplicationContext))) {
			String resourceBasePath = webMergedConfig.getResourceBasePath();
			ResourceLoader resourceLoader = resourceBasePath.startsWith(ResourceLoader.CLASSPATH_URL_PREFIX) ? new DefaultResourceLoader()
					: new FileSystemResourceLoader();

			ServletContext servletContext = new MockServletContext(resourceBasePath, resourceLoader);
			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
			context.setServletContext(servletContext);
		}
		else {
			ServletContext servletContext = null;

			// find the Root WAC
			while (parent != null) {
				if (parent instanceof WebApplicationContext && !(parent.getParent() instanceof WebApplicationContext)) {
					servletContext = ((WebApplicationContext) parent).getServletContext();
					break;
				}
				parent = parent.getParent();
			}
			Assert.state(servletContext != null, "Failed to find Root WebApplicationContext in the context hierarchy");
			context.setServletContext(servletContext);
		}
	}
