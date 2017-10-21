		@Override
		public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request, byte[] body,
				AsyncClientHttpRequestExecution execution) throws IOException {

			ListenableFuture<ClientHttpResponse> future = execution.executeAsync(request, body);
			future.addCallback(
					resp -> {
						response = resp;
						this.latch.countDown();
					},
					ex -> {
						exception = ex;
						this.latch.countDown();
					});
			return future;
		}
