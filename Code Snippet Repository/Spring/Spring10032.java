	@Test
	@Ignore
	public void largeFileUpload() throws Exception {
		Random rnd = new Random();
		ClientHttpResponse response = null;
		try {
			ClientHttpRequest request = factory.createRequest(new URI(baseUrl + "/methods/post"), HttpMethod.POST);
			final int BUF_SIZE = 4096;
			final int ITERATIONS = Integer.MAX_VALUE / BUF_SIZE;
//			final int contentLength = ITERATIONS * BUF_SIZE;
//			request.getHeaders().setContentLength(contentLength);
			OutputStream body = request.getBody();
			for (int i = 0; i < ITERATIONS; i++) {
				byte[] buffer = new byte[BUF_SIZE];
				rnd.nextBytes(buffer);
				body.write(buffer);
			}
			response = request.execute();
			assertEquals("Invalid response status", HttpStatus.OK, response.getStatusCode());
		}
		finally {
			if (response != null) {
				response.close();
			}
		}
	}
