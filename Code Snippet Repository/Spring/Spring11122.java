	@Override
	public <T> T body(BodyExtractor<T, ? super ClientHttpResponse> extractor) {
		return extractor.extract(this.response, new BodyExtractor.Context() {
			@Override
			public List<HttpMessageReader<?>> messageReaders() {
				return strategies.messageReaders();
			}

			@Override
			public Optional<ServerHttpResponse> serverResponse() {
				return Optional.empty();
			}

			@Override
			public Map<String, Object> hints() {
				return Collections.emptyMap();
			}
		});
	}
