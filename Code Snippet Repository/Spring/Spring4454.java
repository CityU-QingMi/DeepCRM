	@Override
	@SuppressWarnings("")
	public Flux<DataBuffer> encode(Publisher<? extends ResourceRegion> inputStream,
			DataBufferFactory bufferFactory, ResolvableType elementType, @Nullable MimeType mimeType,
			@Nullable Map<String, Object> hints) {

		Assert.notNull(inputStream, "'inputStream' must not be null");
		Assert.notNull(bufferFactory, "'bufferFactory' must not be null");
		Assert.notNull(elementType, "'elementType' must not be null");

		if (inputStream instanceof Mono) {
			return ((Mono<? extends ResourceRegion>) inputStream)
					.flatMapMany(region -> writeResourceRegion(region, bufferFactory));
		}
		else {
			Assert.notNull(hints, "'hints' must not be null");
			Assert.isTrue(hints.containsKey(BOUNDARY_STRING_HINT), "'hints' must contain boundaryString hint");
			final String boundaryString = (String) hints.get(BOUNDARY_STRING_HINT);

			byte[] startBoundary = getAsciiBytes("\r\n--" + boundaryString + "\r\n");
			byte[] contentType =
					(mimeType != null ? getAsciiBytes("Content-Type: " + mimeType + "\r\n") : new byte[0]);

			Flux<DataBuffer> regions = Flux.from(inputStream).
					concatMap(region ->
							Flux.concat(
									getRegionPrefix(bufferFactory, startBoundary, contentType, region),
									writeResourceRegion(region, bufferFactory)
							));
			return Flux.concat(regions, getRegionSuffix(bufferFactory, boundaryString));
		}
	}
