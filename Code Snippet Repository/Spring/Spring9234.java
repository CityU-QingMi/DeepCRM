	private HttpAsyncClient startAsyncClient() {
        HttpAsyncClient client = getAsyncClient();
		if (client instanceof CloseableHttpAsyncClient) {
			CloseableHttpAsyncClient closeableAsyncClient = (CloseableHttpAsyncClient) client;
			if (!closeableAsyncClient.isRunning()) {
				closeableAsyncClient.start();
			}
		}
		return client;
	}
