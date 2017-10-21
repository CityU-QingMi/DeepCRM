	private void executeReceiveRequest(final TransportRequest transportRequest,
			final URI url, final HttpHeaders headers, final XhrClientSockJsSession session,
			final SettableListenableFuture<WebSocketSession> connectFuture) {

		if (logger.isTraceEnabled()) {
			logger.trace("Starting XHR receive request for " + url);
		}

		ClientCallback<ClientConnection> clientCallback = new ClientCallback<ClientConnection>() {
			@Override
			public void completed(ClientConnection connection) {
				ClientRequest request = new ClientRequest().setMethod(Methods.POST).setPath(url.getPath());
				HttpString headerName = HttpString.tryFromString(HttpHeaders.HOST);
				request.getRequestHeaders().add(headerName, url.getHost());
				addHttpHeaders(request, headers);
				HttpHeaders httpHeaders = transportRequest.getHttpRequestHeaders();
				connection.sendRequest(request, createReceiveCallback(transportRequest,
						url, httpHeaders, session, connectFuture));
			}

			@Override
			public void failed(IOException ex) {
				throw new SockJsTransportFailureException("Failed to execute request to " + url, ex);
			}
		};

		this.httpClient.connect(clientCallback, url, this.worker, this.bufferPool, this.optionMap);
	}
