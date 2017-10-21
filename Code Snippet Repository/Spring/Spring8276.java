	@Override
	public ClientHttpResponse validateRequestInternal(ClientHttpRequest request) throws IOException {
		RequestExpectation expectation = this.repeatExpectations.findExpectation(request);
		if (expectation == null) {
			if (this.expectationIterator == null || !this.expectationIterator.hasNext()) {
				throw createUnexpectedRequestError(request);
			}
			expectation = this.expectationIterator.next();
			expectation.match(request);
		}
		ClientHttpResponse response = expectation.createResponse(request);
		this.repeatExpectations.update(expectation);
		return response;
	}
