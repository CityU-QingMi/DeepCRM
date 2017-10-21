	@Override
	public org.springframework.http.client.AsyncClientHttpRequestFactory getAsyncRequestFactory() {
		org.springframework.http.client.AsyncClientHttpRequestFactory delegate = super.getAsyncRequestFactory();
		if (!CollectionUtils.isEmpty(getInterceptors())) {
			return new org.springframework.http.client.InterceptingAsyncClientHttpRequestFactory(delegate, getInterceptors());
		}
		else {
			return delegate;
		}
	}
