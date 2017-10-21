	@Override
	public Mono<Resource> transform(ServerWebExchange exchange, Resource inputResource,
			ResourceTransformerChain chain) {

		return chain.transform(exchange, inputResource)
				.flatMap(outputResource -> {
					String name = outputResource.getFilename();
					if (!this.fileExtension.equals(StringUtils.getFilenameExtension(name))) {
						return Mono.just(outputResource);
					}
					DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
					return DataBufferUtils.read(outputResource, bufferFactory, StreamUtils.BUFFER_SIZE)
							.reduce(DataBuffer::write)
							.flatMap(dataBuffer -> {
								CharBuffer charBuffer = DEFAULT_CHARSET.decode(dataBuffer.asByteBuffer());
								DataBufferUtils.release(dataBuffer);
								String content = charBuffer.toString();
								return transform(content, outputResource, chain, exchange);
							});
				});
	}
