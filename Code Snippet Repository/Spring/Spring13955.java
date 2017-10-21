	protected ResponseEntity<String> executeRequest(
			URI url, HttpString method, HttpHeaders headers, @Nullable String body) {

		CountDownLatch latch = new CountDownLatch(1);
		List<ClientResponse> responses = new CopyOnWriteArrayList<>();

		try {
			ClientConnection connection =
					this.httpClient.connect(url, this.worker, this.bufferPool, this.optionMap).get();
			try {
				ClientRequest request = new ClientRequest().setMethod(method).setPath(url.getPath());
				request.getRequestHeaders().add(HttpString.tryFromString(HttpHeaders.HOST), url.getHost());
				if (body != null && !body.isEmpty()) {
					HttpString headerName = HttpString.tryFromString(HttpHeaders.CONTENT_LENGTH);
					request.getRequestHeaders().add(headerName, body.length());
				}
				addHttpHeaders(request, headers);
				connection.sendRequest(request, createRequestCallback(body, responses, latch));

				latch.await();
				ClientResponse response = responses.iterator().next();
				HttpStatus status = HttpStatus.valueOf(response.getResponseCode());
				HttpHeaders responseHeaders = toHttpHeaders(response.getResponseHeaders());
				String responseBody = response.getAttachment(RESPONSE_BODY);
				return (responseBody != null ?
						new ResponseEntity<>(responseBody, responseHeaders, status) :
						new ResponseEntity<>(responseHeaders, status));
			}
			finally {
				IoUtils.safeClose(connection);
			}
		}
		catch (IOException ex) {
			throw new SockJsTransportFailureException("Failed to execute request to " + url, ex);
		}
		catch (InterruptedException ex) {
			throw new SockJsTransportFailureException("Interrupted while processing request to " + url, ex);
		}
	}
