	@Override
	public final ListenableFuture<WebSocketSession> doHandshake(
			WebSocketHandler handler, @Nullable WebSocketHttpHeaders headers, URI url) {

		Assert.notNull(handler, "WebSocketHandler is required");
		Assert.notNull(url, "URL is required");

		String scheme = url.getScheme();
		if (!supportedProtocols.contains(scheme)) {
			throw new IllegalArgumentException("Invalid scheme: '" + scheme + "'");
		}

		SettableListenableFuture<WebSocketSession> connectFuture = new SettableListenableFuture<>();
		try {
			SockJsUrlInfo sockJsUrlInfo = new SockJsUrlInfo(url);
			ServerInfo serverInfo = getServerInfo(sockJsUrlInfo, getHttpRequestHeaders(headers));
			createRequest(sockJsUrlInfo, headers, serverInfo).connect(handler, connectFuture);
		}
		catch (Throwable exception) {
			if (logger.isErrorEnabled()) {
				logger.error("Initial SockJS \"Info\" request to server failed, url=" + url, exception);
			}
			connectFuture.setException(exception);
		}
		return connectFuture;
	}
