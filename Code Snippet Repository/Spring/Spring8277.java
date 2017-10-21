	@Override
	public ClientHttpResponse validateRequestInternal(ClientHttpRequest request) throws IOException {
		RequestExpectation expectation = this.remainingExpectations.findExpectation(request);
		if (expectation == null) {
			throw createUnexpectedRequestError(request);
		}
		ClientHttpResponse response = expectation.createResponse(request);
		this.remainingExpectations.update(expectation);
		return response;
	}
