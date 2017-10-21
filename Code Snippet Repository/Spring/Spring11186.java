	@Override
	public Mono<String> getResourceVersion(Resource resource) {
		return DataBufferUtils.read(resource, dataBufferFactory, StreamUtils.BUFFER_SIZE)
				.reduce(DataBuffer::write)
				.map(buffer -> {
					byte[] result = new byte[buffer.readableByteCount()];
					buffer.read(result);
					DataBufferUtils.release(buffer);
					return DigestUtils.md5DigestAsHex(result);
				});
	}
