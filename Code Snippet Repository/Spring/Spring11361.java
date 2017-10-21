	private void startLazily(HttpServletRequest request) {
		if (this.servletContext != null) {
			return;
		}
		synchronized (this.lifecycleMonitor) {
			if (this.servletContext == null) {
				this.servletContext = request.getServletContext();
				start();
			}
		}
	}
