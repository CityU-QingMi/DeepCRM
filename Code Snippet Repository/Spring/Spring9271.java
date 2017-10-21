	@Override
	public Mono<ClientHttpResponse> connect(HttpMethod method, URI uri,
			Function<? super ClientHttpRequest, Mono<Void>> requestCallback) {

		if (!uri.isAbsolute()) {
			return Mono.error(new IllegalArgumentException("URI is not absolute: " + uri));
		}

		return this.httpClient
				.request(adaptHttpMethod(method),
						uri.toString(),
						request -> requestCallback.apply(adaptRequest(method, uri, request)))
				.map(this::adaptResponse);
	}
