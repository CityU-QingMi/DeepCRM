	@Override
	public final ListenableFuture<WebSocketSession> doHandshake(WebSocketHandler webSocketHandler,
			@Nullable WebSocketHttpHeaders headers, URI uri) {

		Assert.notNull(webSocketHandler, "WebSocketHandler must not be null");
		assertUri(uri);

		if (logger.isDebugEnabled()) {
			logger.debug("Connecting to " + uri);
		}

		HttpHeaders headersToUse = new HttpHeaders();
		if (headers != null) {
			for (String header : headers.keySet()) {
				List<String> values = headers.get(header);
				if (values != null && !specialHeaders.contains(header.toLowerCase())) {
					headersToUse.put(header, values);
				}
			}
		}

		List<String> subProtocols =
				(headers != null ? headers.getSecWebSocketProtocol() : Collections.emptyList());
		List<WebSocketExtension> extensions =
				(headers != null ? headers.getSecWebSocketExtensions() : Collections.emptyList());

		return doHandshakeInternal(webSocketHandler, headersToUse, uri, subProtocols, extensions,
				Collections.emptyMap());
	}
