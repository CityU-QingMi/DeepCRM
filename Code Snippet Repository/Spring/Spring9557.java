	private void extract(@Nullable Class<? extends RestClientException> exceptionClass,
			ClientHttpResponse response) throws IOException {

		if (exceptionClass == null) {
			return;
		}

		HttpMessageConverterExtractor<? extends RestClientException> extractor =
				new HttpMessageConverterExtractor<>(exceptionClass, this.messageConverters);
		RestClientException exception = extractor.extractData(response);
		if (exception != null) {
			throw exception;
		}
	}
