	public void setHost(@Nullable InetSocketAddress host) {
		if (host != null) {
			String value = (host.getPort() != 0 ?
					String.format("%s:%d", host.getHostString(), host.getPort()) : host.getHostString());
			set(HOST, value);
		}
		else {
			set(HOST, null);
		}
	}
