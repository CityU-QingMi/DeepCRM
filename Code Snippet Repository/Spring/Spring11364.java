	@Override
	public Mono<Void> upgrade(ServerWebExchange exchange, WebSocketHandler handler, @Nullable String subProtocol) {
		ServerHttpRequest request = exchange.getRequest();
		Assert.isInstanceOf(AbstractServerHttpRequest.class, request);
		HttpServerExchange httpExchange = ((AbstractServerHttpRequest) request).getNativeRequest();

		Set<String> protocols = (subProtocol != null ? Collections.singleton(subProtocol) : Collections.emptySet());
		Hybi13Handshake handshake = new Hybi13Handshake(protocols, false);
		List<Handshake> handshakes = Collections.singletonList(handshake);

		URI url = request.getURI();
		HttpHeaders headers = request.getHeaders();
		Mono<Principal> principal = exchange.getPrincipal();
		HandshakeInfo info = new HandshakeInfo(url, headers, principal, subProtocol);
		DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();

		try {
			DefaultCallback callback = new DefaultCallback(info, handler, bufferFactory);
			new WebSocketProtocolHandshakeHandler(handshakes, callback).handleRequest(httpExchange);
		}
		catch (Exception ex) {
			return Mono.error(ex);
		}

		return Mono.empty();
	}
