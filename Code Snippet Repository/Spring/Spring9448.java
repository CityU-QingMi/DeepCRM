		public DefaultServerHttpRequest(URI uri, @Nullable String contextPath,
				HttpHeaders headers, String methodValue, MultiValueMap<String, HttpCookie> cookies,
				@Nullable InetSocketAddress remoteAddress,
				Flux<DataBuffer> body, ServerHttpRequest originalRequest) {

			super(uri, contextPath, headers);
			this.methodValue = methodValue;
			this.cookies = cookies;
			this.remoteAddress = remoteAddress;
			this.body = body;
			this.originalRequest = originalRequest;
		}
