	@Override
	public Mono<Resource> transform(ServerWebExchange exchange, Resource inputResource,
			ResourceTransformerChain transformerChain) {

		return transformerChain.transform(exchange, inputResource)
				.flatMap(ouptputResource -> {
					String filename = ouptputResource.getFilename();
					if (!"css".equals(StringUtils.getFilenameExtension(filename)) ||
							inputResource instanceof GzipResourceResolver.GzippedResource) {
						return Mono.just(ouptputResource);
					}

					if (logger.isTraceEnabled()) {
						logger.trace("Transforming resource: " + ouptputResource);
					}

					DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
					return DataBufferUtils.read(ouptputResource, bufferFactory, StreamUtils.BUFFER_SIZE)
							.reduce(DataBuffer::write)
							.flatMap(dataBuffer -> {
								CharBuffer charBuffer = DEFAULT_CHARSET.decode(dataBuffer.asByteBuffer());
								DataBufferUtils.release(dataBuffer);
								String cssContent = charBuffer.toString();
								return transformContent(cssContent, ouptputResource, transformerChain, exchange);
							});
				});
	}
