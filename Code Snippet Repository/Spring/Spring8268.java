	@Override
	public ClientHttpResponse validateRequest(ClientHttpRequest request) throws IOException {
		List<ClientHttpRequest> requests = this.requests;
		synchronized (requests) {
			if (requests.isEmpty()) {
				afterExpectationsDeclared();
			}
			ClientHttpResponse response = validateRequestInternal(request);
			requests.add(request);
			return response;
		}
	}
