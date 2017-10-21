		@Override
		public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
			Exception error = new UnsupportedOperationException();
			String path = request.getURI().getPath();
			if (path.endsWith("response-body-error")) {
				return response.writeWith(Mono.error(error));
			}
			else if (path.endsWith("handling-error")) {
				return Mono.error(error);
			}
			else {
				return Mono.empty();
			}
		}
