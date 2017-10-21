	public static BodyInserter<MultiValueMap<String, ?>, ClientHttpRequest> fromMultipartData(
			MultiValueMap<String, ?> multipartData) {

		Assert.notNull(multipartData, "'multipartData' must not be null");
		return (outputMessage, context) -> {
			HttpMessageWriter<MultiValueMap<String, ?>> messageWriter =
					findMessageWriter(context, MULTIPART_VALUE_TYPE, MediaType.MULTIPART_FORM_DATA);
			return messageWriter.write(Mono.just(multipartData), FORM_TYPE,
					MediaType.MULTIPART_FORM_DATA, outputMessage, context.hints());
		};
	}
