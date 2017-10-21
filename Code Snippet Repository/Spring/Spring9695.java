	protected ServletContext getServletContext() {
		if (this.filterConfig != null) {
			return this.filterConfig.getServletContext();
		}
		else if (this.servletContext != null) {
			return this.servletContext;
		}
		else {
			throw new IllegalStateException("No ServletContext");
		}
	}
