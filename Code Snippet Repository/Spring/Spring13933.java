	public DefaultTransportRequest(SockJsUrlInfo sockJsUrlInfo,
			@Nullable HttpHeaders handshakeHeaders, @Nullable HttpHeaders httpRequestHeaders,
			Transport transport, TransportType serverTransportType, SockJsMessageCodec codec) {

		Assert.notNull(sockJsUrlInfo, "SockJsUrlInfo is required");
		Assert.notNull(transport, "Transport is required");
		Assert.notNull(serverTransportType, "TransportType is required");
		Assert.notNull(codec, "SockJsMessageCodec is required");
		this.sockJsUrlInfo = sockJsUrlInfo;
		this.handshakeHeaders = (handshakeHeaders != null ? handshakeHeaders : new HttpHeaders());
		this.httpRequestHeaders = (httpRequestHeaders != null ? httpRequestHeaders : new HttpHeaders());
		this.transport = transport;
		this.serverTransportType = serverTransportType;
		this.codec = codec;
	}
