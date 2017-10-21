		@Override
		public <T> T execute(URI url, HttpMethod method, @Nullable RequestCallback callback, @Nullable ResponseExtractor<T> extractor) throws RestClientException {
			try {
				extractor.extractData(this.responses.remove());
			}
			catch (Throwable t) {
				throw new RestClientException("Failed to invoke extractor", t);
			}
			return null;
		}
