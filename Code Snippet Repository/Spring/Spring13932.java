	@Override
	public void executeSendRequest(URI url, HttpHeaders headers, TextMessage message) {
		if (logger.isTraceEnabled()) {
			logger.trace("Starting XHR send, url=" + url);
		}
		ResponseEntity<String> response = executeSendRequestInternal(url, headers, message);
		if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
			if (logger.isErrorEnabled()) {
				logger.error("XHR send request (url=" + url + ") failed: " + response);
			}
			throw new HttpServerErrorException(response.getStatusCode());
		}
		if (logger.isTraceEnabled()) {
			logger.trace("XHR send request (url=" + url + ") response: " + response);
		}
	}
