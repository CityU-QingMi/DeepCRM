		@Override
		public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
			String path = request.getURI().getPath();
			if (path.endsWith("write-and-flush")) {
				Flux<Publisher<DataBuffer>> responseBody = Flux
						.interval(Duration.ofMillis(50))
						.map(l -> toDataBuffer("data" + l, response.bufferFactory()))
						.take(2)
						.map(Flux::just);
				responseBody = responseBody.concatWith(Flux.never());
				return response.writeAndFlushWith(responseBody);
			}
			else if (path.endsWith("write-and-complete")) {
				Flux<DataBuffer> responseBody = Flux
						.just("0123456789")
						.repeat(20000)
						.map(value -> toDataBuffer(value, response.bufferFactory()));
				return response.writeWith(responseBody);
			}
			else if (path.endsWith("write-and-never-complete")) {
				Flux<DataBuffer> responseBody = Flux
						.just("0123456789")
						.repeat(20000)
						.map(value -> toDataBuffer(value, response.bufferFactory()))
						.mergeWith(Flux.never());
				return response.writeWith(responseBody);
			}
			return response.writeWith(Flux.empty());
		}
