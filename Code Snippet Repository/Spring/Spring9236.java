	@Override
	public void destroy() throws Exception {
		try {
			super.destroy();
		}
		finally {
			HttpAsyncClient asyncClient = getAsyncClient();
			if (asyncClient instanceof Closeable) {
				((Closeable) asyncClient).close();
			}
		}
	}
