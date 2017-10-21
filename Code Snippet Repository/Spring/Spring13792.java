	public StandardWebSocketSession(@Nullable HttpHeaders headers, @Nullable Map<String, Object> attributes,
			@Nullable InetSocketAddress localAddress, @Nullable InetSocketAddress remoteAddress,
			@Nullable Principal user) {

		super(attributes);
		headers = (headers != null ? headers : new HttpHeaders());
		this.handshakeHeaders = HttpHeaders.readOnlyHttpHeaders(headers);
		this.user = user;
		this.localAddress = localAddress;
		this.remoteAddress = remoteAddress;
	}
