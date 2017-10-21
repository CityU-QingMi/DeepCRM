	@Override
	public Mono<ClientHttpResponse> connect(HttpMethod method, URI uri,
			Function<? super ClientHttpRequest, Mono<Void>> requestCallback) {

		AtomicReference<WiretapClientHttpRequest> requestRef = new AtomicReference<>();

		return this.delegate
				.connect(method, uri, request -> {
					WiretapClientHttpRequest wrapped = new WiretapClientHttpRequest(request);
					requestRef.set(wrapped);
					return requestCallback.apply(wrapped);
				})
				.map(response ->  {
					WiretapClientHttpRequest wrappedRequest = requestRef.get();
					String requestId = wrappedRequest.getHeaders().getFirst(WebTestClient.WEBTESTCLIENT_REQUEST_ID);
					Assert.state(requestId != null, () -> "No \"" + WebTestClient.WEBTESTCLIENT_REQUEST_ID + "\" header");
					WiretapClientHttpResponse wrappedResponse = new WiretapClientHttpResponse(response);
					ExchangeResult result = new ExchangeResult(wrappedRequest, wrappedResponse);
					this.exchanges.put(requestId, result);
					return wrappedResponse;
				});
	}
