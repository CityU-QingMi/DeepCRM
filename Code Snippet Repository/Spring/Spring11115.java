	public static BodyInserter<MultiValueMap<String, String>, ClientHttpRequest> fromFormData(
			MultiValueMap<String, String> formData) {

		Assert.notNull(formData, "'formData' must not be null");
		return (outputMessage, context) -> {
			HttpMessageWriter<MultiValueMap<String, String>> messageWriter =
					findMessageWriter(context, FORM_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
			return messageWriter.write(Mono.just(formData), FORM_TYPE,
					MediaType.APPLICATION_FORM_URLENCODED, outputMessage, context.hints());
		};
	}
