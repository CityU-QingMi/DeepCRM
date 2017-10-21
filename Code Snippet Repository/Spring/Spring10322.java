	@Override
	public int getServerPort() {
		String host = getHeader(HttpHeaders.HOST);
		if (host != null) {
			host = host.trim();
			int idx;
			if (host.startsWith("[")) {
				idx = host.indexOf(':', host.indexOf(']'));
			}
			else {
				idx = host.indexOf(':');
			}
			if (idx != -1) {
				return Integer.parseInt(host.substring(idx + 1));
			}
		}

		// else
		return this.serverPort;
	}
