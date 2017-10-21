	private void ports(UriComponents uriComponents, MockHttpServletRequest request) {
		int serverPort = uriComponents.getPort();
		request.setServerPort(serverPort);
		if (serverPort == -1) {
			int portConnection = this.webRequest.getUrl().getDefaultPort();
			request.setLocalPort(serverPort);
			request.setRemotePort(portConnection);
		}
		else {
			request.setRemotePort(serverPort);
		}
	}
