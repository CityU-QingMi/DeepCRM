	@Override
	protected Mono<Void> writeAndFlushWithInternal(
			Publisher<? extends Publisher<? extends DataBuffer>> body) {
		Flux<ByteBuf> bodyWithFlushSignals = Flux.from(body).
				flatMap(publisher -> Flux.from(publisher).
						map(NettyDataBufferFactory::toByteBuf).
						concatWith(Mono.just(FLUSH_SIGNAL)));
		Observable<ByteBuf> content = RxReactiveStreams.toObservable(bodyWithFlushSignals);
		ResponseContentWriter<ByteBuf> writer = this.response.write(content, bb -> bb == FLUSH_SIGNAL);
		return Flux.from(RxReactiveStreams.toPublisher(writer)).then();
	}
