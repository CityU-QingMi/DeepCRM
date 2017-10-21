	@Override
	protected ListenableFuture<ClientHttpResponse> executeInternal(
			final HttpHeaders headers, final byte[] bufferedOutput) throws IOException {

		return this.taskExecutor.submitListenable(new Callable<ClientHttpResponse>() {
			@Override
			public ClientHttpResponse call() throws Exception {
				SimpleBufferingClientHttpRequest.addHeaders(connection, headers);
				// JDK <1.8 doesn't support getOutputStream with HTTP DELETE
				if (HttpMethod.DELETE == getMethod() && bufferedOutput.length == 0) {
					connection.setDoOutput(false);
				}
				if (connection.getDoOutput() && outputStreaming) {
					connection.setFixedLengthStreamingMode(bufferedOutput.length);
				}
				connection.connect();
				if (connection.getDoOutput()) {
					FileCopyUtils.copy(bufferedOutput, connection.getOutputStream());
				}
				else {
					// Immediately trigger the request in a no-output scenario as well
					connection.getResponseCode();
				}
				return new SimpleClientHttpResponse(connection);
			}
		});
	}
