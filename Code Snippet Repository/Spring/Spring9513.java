	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.server == null) {
			InetSocketAddress address = (this.hostname != null ?
					new InetSocketAddress(this.hostname, this.port) : new InetSocketAddress(this.port));
			HttpServer server = HttpServer.create(address, this.backlog);
			if (this.logger.isInfoEnabled()) {
				this.logger.info("Starting HttpServer at address " + address);
			}
			server.start();
			this.server = server;
			this.localServer = true;
		}
		super.afterPropertiesSet();
	}
