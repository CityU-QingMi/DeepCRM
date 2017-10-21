	@Override
	public void close() {
        // Release underlying connection back to the connection manager
        try {
            try {
                // Attempt to keep connection alive by consuming its remaining content
                EntityUtils.consume(this.httpResponse.getEntity());
            }
			finally {
				if (this.httpResponse instanceof Closeable) {
					((Closeable) this.httpResponse).close();
				}
            }
        }
        catch (IOException ex) {
			// Ignore exception on close...
        }
	}
