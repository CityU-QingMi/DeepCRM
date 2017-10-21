		@Override
		protected Mono<Void> writeAndFlushWithInternal(
				Publisher<? extends Publisher<? extends DataBuffer>> bodyWithFlush) {
			return Flux.from(bodyWithFlush).flatMap(body ->
				Flux.from(body).map(b -> {
					this.body.add(b);
					return b;
				})
			).then();
		}
