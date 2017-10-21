	protected ResponseEntity<String> executeRequest(URI url, HttpMethod method, HttpHeaders headers, @Nullable String body) {
		Request httpRequest = this.httpClient.newRequest(url).method(method);
		addHttpHeaders(httpRequest, headers);
		if (body != null) {
			httpRequest.content(new StringContentProvider(body));
		}
		ContentResponse response;
		try {
			response = httpRequest.send();
		}
		catch (Exception ex) {
			throw new SockJsTransportFailureException("Failed to execute request to " + url, ex);
		}
		HttpStatus status = HttpStatus.valueOf(response.getStatus());
		HttpHeaders responseHeaders = toHttpHeaders(response.getHeaders());
		return (response.getContent() != null ?
				new ResponseEntity<>(response.getContentAsString(), responseHeaders, status) :
				new ResponseEntity<>(responseHeaders, status));
	}
