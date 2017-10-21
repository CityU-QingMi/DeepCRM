	@Override
	protected ClientHttpResponse executeInternal(HttpHeaders headers) throws IOException {
		try {
			if (this.body != null) {
				this.body.close();
			}
			else {
				SimpleBufferingClientHttpRequest.addHeaders(this.connection, headers);
				this.connection.connect();
				// Immediately trigger the request in a no-output scenario as well
				this.connection.getResponseCode();
			}
		}
		catch (IOException ex) {
			// ignore
		}
		return new SimpleClientHttpResponse(this.connection);
	}
