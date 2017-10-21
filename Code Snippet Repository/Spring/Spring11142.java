	@Override
	public <T> T body(BodyExtractor<T, ? super ServerHttpRequest> extractor, Map<String, Object> hints) {
		Assert.notNull(extractor, "'extractor' must not be null");
		return extractor.extract(request(),
				new BodyExtractor.Context() {
					@Override
					public List<HttpMessageReader<?>> messageReaders() {
						return messageReaders;
					}
					@Override
					public Optional<ServerHttpResponse> serverResponse() {
						return Optional.of(exchange().getResponse());
					}
					@Override
					public Map<String, Object> hints() {
						return hints;
					}
				});
	}
