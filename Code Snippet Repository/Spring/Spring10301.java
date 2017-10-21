	private MockServerHttpRequest(HttpMethod httpMethod, URI uri, @Nullable String contextPath,
			HttpHeaders headers, MultiValueMap<String, HttpCookie> cookies,
			@Nullable InetSocketAddress remoteAddress, Publisher<? extends DataBuffer> body) {

		super(uri, contextPath, headers);
		this.httpMethod = httpMethod;
		this.cookies = cookies;
		this.remoteAddress = remoteAddress;
		this.body = Flux.from(body);
	}
