	private DefaultTransportRequest createRequest(
			SockJsUrlInfo urlInfo, @Nullable HttpHeaders headers, ServerInfo serverInfo) {

		List<DefaultTransportRequest> requests = new ArrayList<>(this.transports.size());
		for (Transport transport : this.transports) {
			for (TransportType type : transport.getTransportTypes()) {
				if (serverInfo.isWebSocketEnabled() || !TransportType.WEBSOCKET.equals(type)) {
					requests.add(new DefaultTransportRequest(urlInfo, headers, getHttpRequestHeaders(headers),
							transport, type, getMessageCodec()));
				}
			}
		}
		if (CollectionUtils.isEmpty(requests)) {
			throw new IllegalStateException(
					"No transports: " + urlInfo + ", webSocketEnabled=" + serverInfo.isWebSocketEnabled());
		}
		for (int i = 0; i < requests.size() - 1; i++) {
			DefaultTransportRequest request = requests.get(i);
			Principal user = getUser();
			if (user != null) {
				request.setUser(user);
			}
			if (this.connectTimeoutScheduler != null) {
				request.setTimeoutValue(serverInfo.getRetransmissionTimeout());
				request.setTimeoutScheduler(this.connectTimeoutScheduler);
			}
			request.setFallbackRequest(requests.get(i + 1));
		}
		return requests.get(0);
	}
