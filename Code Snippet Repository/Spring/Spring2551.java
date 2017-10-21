	@Override
	public void afterPropertiesSet() throws IOException {
		InetSocketAddress address = (this.hostname != null ?
				new InetSocketAddress(this.hostname, this.port) : new InetSocketAddress(this.port));
		this.server = HttpServer.create(address, this.backlog);
		if (this.executor != null) {
			this.server.setExecutor(this.executor);
		}
		if (this.contexts != null) {
			for (String key : this.contexts.keySet()) {
				HttpContext httpContext = this.server.createContext(key, this.contexts.get(key));
				if (this.filters != null) {
					httpContext.getFilters().addAll(this.filters);
				}
				if (this.authenticator != null) {
					httpContext.setAuthenticator(this.authenticator);
				}
			}
		}
		if (this.logger.isInfoEnabled()) {
			this.logger.info("Starting HttpServer at address " + address);
		}
		this.server.start();
	}
