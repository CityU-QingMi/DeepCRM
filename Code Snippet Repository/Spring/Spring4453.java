	@Override
	public Mono<Resource> decodeToMono(Publisher<DataBuffer> inputStream, ResolvableType elementType,
			@Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

		Class<?> clazz = elementType.getRawClass();
		Assert.state(clazz != null, "No resource class");

		Mono<byte[]> byteArray = Flux.from(inputStream).
				reduce(DataBuffer::write).
				map(dataBuffer -> {
					byte[] bytes = new byte[dataBuffer.readableByteCount()];
					dataBuffer.read(bytes);
					DataBufferUtils.release(dataBuffer);
					return bytes;
				});


		if (InputStreamResource.class == clazz) {
			return Mono.from(byteArray.map(ByteArrayInputStream::new).map(InputStreamResource::new));
		}
		else if (clazz.isAssignableFrom(ByteArrayResource.class)) {
			return Mono.from(byteArray.map(ByteArrayResource::new));
		}
		else {
			return Mono.error(new IllegalStateException("Unsupported resource class: " + clazz));
		}
	}
