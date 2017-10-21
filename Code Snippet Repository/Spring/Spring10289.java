		@Override
		public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
			URI uri = request.getURI();
			assertEquals("https", uri.getScheme());
			assertNotNull(uri.getHost());
			assertNotEquals(-1, uri.getPort());
			assertNotNull(request.getRemoteAddress());
			assertEquals("/foo", uri.getPath());
			assertEquals("param=bar", uri.getQuery());
			return Mono.empty();
		}
