		private static Mono<WebClientResponseException> createResponseException(ClientResponse response) {

			return response.body(BodyExtractors.toDataBuffers())
					.reduce(DataBuffer::write)
					.map(dataBuffer -> {
						byte[] bytes = new byte[dataBuffer.readableByteCount()];
						dataBuffer.read(bytes);
						DataBufferUtils.release(dataBuffer);
						return bytes;
					})
					.defaultIfEmpty(new byte[0])
					.map(bodyBytes -> {
						String msg = String.format("ClientResponse has erroneous status code: %d %s", response.statusCode().value(),
								response.statusCode().getReasonPhrase());
						Charset charset = response.headers().contentType()
								.map(MimeType::getCharset)
								.orElse(StandardCharsets.ISO_8859_1);
						return new WebClientResponseException(msg,
								response.statusCode().value(),
								response.statusCode().getReasonPhrase(),
								response.headers().asHttpHeaders(),
								bodyBytes,
								charset
								);
					});
		}
