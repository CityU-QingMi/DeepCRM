	@Override
	public String executeInfoRequest(URI infoUrl, @Nullable HttpHeaders headers) {
		if (logger.isDebugEnabled()) {
			logger.debug("Executing SockJS Info request, url=" + infoUrl);
		}
		HttpHeaders infoRequestHeaders = new HttpHeaders();
		if (headers != null) {
			infoRequestHeaders.putAll(headers);
		}
		ResponseEntity<String> response = executeInfoRequestInternal(infoUrl, infoRequestHeaders);
		if (response.getStatusCode() != HttpStatus.OK) {
			if (logger.isErrorEnabled()) {
				logger.error("SockJS Info request (url=" + infoUrl + ") failed: " + response);
			}
			throw new HttpServerErrorException(response.getStatusCode());
		}
		if (logger.isTraceEnabled()) {
			logger.trace("SockJS Info request (url=" + infoUrl + ") response: " + response);
		}
		String result = response.getBody();
		return (result != null ? result : "");
	}
