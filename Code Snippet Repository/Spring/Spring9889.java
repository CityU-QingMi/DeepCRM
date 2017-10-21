	private void adaptForwardedHost(String hostToUse) {
		int portSeparatorIdx = hostToUse.lastIndexOf(":");
		if (portSeparatorIdx > hostToUse.lastIndexOf("]")) {
			host(hostToUse.substring(0, portSeparatorIdx));
			port(Integer.parseInt(hostToUse.substring(portSeparatorIdx + 1)));
		}
		else {
			host(hostToUse);
			port(null);
		}
	}
