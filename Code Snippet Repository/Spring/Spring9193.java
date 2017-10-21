	@Nullable
	public InetSocketAddress getHost() {
		String value = getFirst(HOST);
		if (value == null) {
			return null;
		}
		final int idx;
		if (value.startsWith("[")) {
			idx = value.indexOf(':', value.indexOf(']'));
		} else {
			idx = value.lastIndexOf(':');
		}
		String hostname = null;
		int port = 0;
		if (idx != -1 && idx < value.length() - 1) {
			hostname = value.substring(0, idx);
			String portString = value.substring(idx + 1);
			try {
				port = Integer.parseInt(portString);
			}
			catch (NumberFormatException ex) {
				// ignored
			}
		}
		if (hostname == null) {
			hostname = value;
		}
		return InetSocketAddress.createUnresolved(hostname, port);
	}
