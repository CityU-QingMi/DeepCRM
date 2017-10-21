	private static String generateId() {
		String host;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException ex) {
			host = "unknown";
		}
		return host + '-' + UUID.randomUUID();
	}
