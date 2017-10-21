	@Override
	public boolean matches(WebRequest request) {
		URL url = request.getUrl();
		String host = url.getHost();

		if (this.hosts.contains(host)) {
			return true;
		}

		int port = url.getPort();
		if (port == -1) {
			port = url.getDefaultPort();
		}
		String hostAndPort = host + ":" + port;

		return this.hosts.contains(hostAndPort);
	}
