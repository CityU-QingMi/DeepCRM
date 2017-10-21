		@Override
		public ClientHttpResponse execute(HttpRequest request, byte[] body) throws IOException {
			if (this.iterator.hasNext()) {
				ClientHttpRequestInterceptor nextInterceptor = this.iterator.next();
				return nextInterceptor.intercept(request, body, this);
			}
			else {
				HttpMethod method = request.getMethod();
				Assert.state(method != null, "No standard HTTP method");
				ClientHttpRequest delegate = requestFactory.createRequest(request.getURI(), method);
				for (Map.Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
					delegate.getHeaders().addAll(entry.getKey(), entry.getValue());
				}
				if (body.length > 0) {
					StreamUtils.copy(body, delegate.getBody());
				}
				return delegate.execute();
			}
		}
