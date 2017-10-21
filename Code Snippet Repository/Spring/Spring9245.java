		@Override
		public ListenableFuture<ClientHttpResponse> executeAsync(HttpRequest request, byte[] body)
				throws IOException {

			if (this.iterator.hasNext()) {
				AsyncClientHttpRequestInterceptor interceptor = this.iterator.next();
				return interceptor.intercept(request, body, this);
			}
			else {
				URI uri = request.getURI();
				HttpMethod method = request.getMethod();
				HttpHeaders headers = request.getHeaders();

				Assert.state(method != null, "No standard HTTP method");
				AsyncClientHttpRequest delegate = requestFactory.createAsyncRequest(uri, method);
				delegate.getHeaders().putAll(headers);
				if (body.length > 0) {
					StreamUtils.copy(body, delegate.getBody());
				}

				return delegate.executeAsync();
			}
		}
