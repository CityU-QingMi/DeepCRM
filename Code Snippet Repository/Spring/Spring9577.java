	@Override
	public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType)
			throws RestClientException {

		Assert.notNull(requestEntity, "RequestEntity must not be null");

		RequestCallback requestCallback = httpEntityCallback(requestEntity, responseType);
		ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
		return nonNull(execute(requestEntity.getUrl(), requestEntity.getMethod(), requestCallback, responseExtractor));
	}
