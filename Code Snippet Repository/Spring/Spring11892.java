		@Override
		public Mono<Void> render(@Nullable Map<String, ?> model, @Nullable MediaType mediaType, ServerWebExchange exchange) {
			ServerHttpResponse response = exchange.getResponse();
			if (mediaType != null) {
				response.getHeaders().setContentType(mediaType);
			}
			model = new TreeMap<>(model);
			String value = this.name + ": " + model.toString();
			ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes(UTF_8));
			DataBuffer dataBuffer = new DefaultDataBufferFactory().wrap(byteBuffer);
			return response.writeWith(Flux.just(dataBuffer));
		}
