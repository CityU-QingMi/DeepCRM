	private static int getPort(URI uri) {
		int port = uri.getPort();
		if (port == -1) {
			if ("http".equalsIgnoreCase(uri.getScheme())) {
				port = 80;
			}
			else if ("https".equalsIgnoreCase(uri.getScheme())) {
				port = 443;
			}
		}
		return port;
	}
