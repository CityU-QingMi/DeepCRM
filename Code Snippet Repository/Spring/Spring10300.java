	public Mono<String> getBodyAsString() {
		Charset charset = getCharset();
		return Flux.from(getBody())
				.reduce(bufferFactory.allocateBuffer(), (previous, current) -> {
					previous.write(current);
					DataBufferUtils.release(current);
					return previous;
				})
				.map(buffer -> dumpString(buffer, charset));
	}
