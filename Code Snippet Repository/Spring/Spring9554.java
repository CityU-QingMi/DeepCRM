	protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
		switch (statusCode.series()) {
			case CLIENT_ERROR:
				throw new HttpClientErrorException(statusCode, response.getStatusText(),
						response.getHeaders(), getResponseBody(response), getCharset(response));
			case SERVER_ERROR:
				throw new HttpServerErrorException(statusCode, response.getStatusText(),
						response.getHeaders(), getResponseBody(response), getCharset(response));
			default:
				throw new UnknownHttpStatusCodeException(statusCode.value(), response.getStatusText(),
						response.getHeaders(), getResponseBody(response), getCharset(response));
		}
	}
