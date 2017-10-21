	protected org.springframework.http.client.AsyncClientHttpRequest createAsyncRequest(URI url, HttpMethod method)
			throws IOException {

		org.springframework.http.client.AsyncClientHttpRequest request =
				getAsyncRequestFactory().createAsyncRequest(url, method);
		if (logger.isDebugEnabled()) {
			logger.debug("Created asynchronous " + method.name() + " request for \"" + url + "\"");
		}
		return request;
	}
