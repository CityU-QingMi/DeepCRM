	@SuppressWarnings("")
	private <T> Flux<DataBuffer> encodePart(byte[] boundary, String name, T value) {
		MultipartHttpOutputMessage outputMessage = new MultipartHttpOutputMessage(this.bufferFactory, getCharset());

		T body;
		if (value instanceof HttpEntity) {
			outputMessage.getHeaders().putAll(((HttpEntity<T>) value).getHeaders());
			body = ((HttpEntity<T>) value).getBody();
			Assert.state(body != null, "MultipartHttpMessageWriter only supports HttpEntity with body");
		}
		else {
			body = value;
		}

		String filename = (body instanceof Resource ? ((Resource) body).getFilename() : null);
		outputMessage.getHeaders().setContentDispositionFormData(name, filename);

		ResolvableType bodyType = ResolvableType.forClass(body.getClass());
		MediaType contentType = outputMessage.getHeaders().getContentType();

		Optional<HttpMessageWriter<?>> writer = this.partWriters.stream()
				.filter(partWriter -> partWriter.canWrite(bodyType, contentType))
				.findFirst();

		if (!writer.isPresent()) {
			return Flux.error(new CodecException("No suitable writer found for part: " + name));
		}

		Mono<Void> partWritten = ((HttpMessageWriter<T>) writer.get())
				.write(Mono.just(body), bodyType, contentType, outputMessage, Collections.emptyMap());

		// partWritten.subscribe() is required in order to make sure MultipartHttpOutputMessage#getBody()
		// returns a non-null value (occurs with ResourceHttpMessageWriter that invokes
		// ReactiveHttpOutputMessage.writeWith() only when at least one element has been requested).
		partWritten.subscribe();

		return Flux.concat(
				Mono.just(generateBoundaryLine(boundary)), outputMessage.getBody(), Mono.just(generateNewLine()));
	}
