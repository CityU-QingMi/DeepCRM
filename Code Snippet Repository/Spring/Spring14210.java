	private ListenableFuture<WebSocketSession> connect(RestOperations restTemplate, ClientHttpResponse... responses)
			throws Exception {

		RestTemplateXhrTransport transport = new RestTemplateXhrTransport(restTemplate);
		transport.setTaskExecutor(new SyncTaskExecutor());

		SockJsUrlInfo urlInfo = new SockJsUrlInfo(new URI("http://example.com"));
		HttpHeaders headers = new HttpHeaders();
		headers.add("h-foo", "h-bar");
		TransportRequest request = new DefaultTransportRequest(urlInfo, headers, headers,
				transport, TransportType.XHR, CODEC);

		return transport.connect(request, this.webSocketHandler);
	}
