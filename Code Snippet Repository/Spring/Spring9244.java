	@Override
	protected ClientHttpResponse executeInternal(HttpHeaders headers) throws IOException {
		HttpComponentsClientHttpRequest.addHeaders(this.httpRequest, headers);

		if (this.httpRequest instanceof HttpEntityEnclosingRequest && this.body != null) {
			HttpEntityEnclosingRequest entityEnclosingRequest = (HttpEntityEnclosingRequest) this.httpRequest;
			HttpEntity requestEntity = new StreamingHttpEntity(getHeaders(), this.body);
			entityEnclosingRequest.setEntity(requestEntity);
		}

		HttpResponse httpResponse = this.httpClient.execute(this.httpRequest, this.httpContext);
		return new HttpComponentsClientHttpResponse(httpResponse);
	}
