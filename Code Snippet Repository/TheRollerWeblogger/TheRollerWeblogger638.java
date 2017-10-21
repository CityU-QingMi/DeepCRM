	public void init(ExtendedProperties configuration) {

		if (log.isDebugEnabled()) {
			log.debug("WebappResourceLoader: initialization starting.");
        }

		// get configured paths
		paths = configuration.getStringArray("path");
		if (paths == null || paths.length == 0) {
			paths = new String[1];
			paths[0] = "/";
		} else {
			// make sure the paths end with a '/'
			for (int i = 0; i < paths.length; i++) {
				if (!paths[i].endsWith("/")) {
					paths[i] += '/';
				}
				if (log.isDebugEnabled()) {
                    log.debug("WebappResourceLoader: added template path - '"
                            + paths[i] + "'");
                }
			}
		}

		// get the ServletContext
		servletContext = RollerContext.getServletContext();

		if (log.isDebugEnabled()) {
			log.debug("Servlet Context = "
					+ servletContext.getRealPath("/WEB-INF/velocity/"));
        }

		// init the template paths map
		templatePaths = new HashMap<String, String>();

		if (log.isDebugEnabled()) {
            log.debug("WebappResourceLoader: initialization complete.");
        }
	}
