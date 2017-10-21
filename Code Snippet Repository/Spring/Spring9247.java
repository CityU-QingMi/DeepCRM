	@Override
	public ClientHttpResponse execute() throws IOException {
		try {
			return executeAsync().get();
		}
		catch (InterruptedException ex) {
			throw new IOException(ex.getMessage(), ex);
		}
		catch (ExecutionException ex) {
			if (ex.getCause() instanceof IOException) {
				throw (IOException) ex.getCause();
			}
			else {
				throw new IOException(ex.getMessage(), ex.getCause());
			}
		}
	}
