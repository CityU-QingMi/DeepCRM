		@Override
		public Mono<Void> render(@Nullable Map<String, ?> model, @Nullable MediaType contentType,
				ServerWebExchange exchange) {
			StringBuilder builder = new StringBuilder();
			builder.append("name=").append(this.name).append('\n');
			for (Map.Entry<String, ?> entry : model.entrySet()) {
				builder.append(entry.getKey()).append('=').append(entry.getValue()).append('\n');
			}
			builder.setLength(builder.length() - 1);
			byte[] bytes = builder.toString().getBytes(StandardCharsets.UTF_8);

			ServerHttpResponse response = exchange.getResponse();
			DataBuffer buffer = response.bufferFactory().wrap(bytes);
			response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
			return response.writeWith(Mono.just(buffer));
		}
