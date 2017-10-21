		@Override
		public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
			Mono<Integer> requestSizeMono = request.getBody().
					reduce(0, (integer, dataBuffer) -> integer +
							dataBuffer.readableByteCount()).
					doOnSuccessOrError((size, throwable) -> {
						assertNull(throwable);
						assertEquals(REQUEST_SIZE, (long) size);
					});

			response.getHeaders().setContentLength(RESPONSE_SIZE);

			return requestSizeMono.then(response.writeWith(multipleChunks()));
		}
