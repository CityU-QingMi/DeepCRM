	@Override
	public String getServerName() {
		String host = getHeader(HttpHeaders.HOST);
		if (host != null) {
			host = host.trim();
			if (host.startsWith("[")) {
				host = host.substring(1, host.indexOf(']'));
			}
			else if (host.contains(":")) {
				host = host.substring(0, host.indexOf(':'));
			}
			return host;
		}

		// else
		return this.serverName;
	}
