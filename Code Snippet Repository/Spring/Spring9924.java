	private static int getPort(UriComponents uri) {
		int port = uri.getPort();
		if (port == -1) {
			if ("http".equals(uri.getScheme()) || "ws".equals(uri.getScheme())) {
				port = 80;
			}
			else if ("https".equals(uri.getScheme()) || "wss".equals(uri.getScheme())) {
				port = 443;
			}
		}
		return port;
	}
