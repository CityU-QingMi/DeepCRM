	@Override
	public void destroy() throws IOException {
		if (this.defaultClient) {
			// Clean up the client if we created it in the constructor
			if (this.client.cache() != null) {
				this.client.cache().close();
			}
			this.client.dispatcher().executorService().shutdown();
		}
	}
