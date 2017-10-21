	@Test(expected = IllegalStateException.class)
	public void multipleWrites() throws Exception {
		ClientHttpRequest request = factory.createRequest(new URI(baseUrl + "/echo"), HttpMethod.POST);
		final byte[] body = "Hello World".getBytes("UTF-8");
		if (request instanceof StreamingHttpOutputMessage) {
			StreamingHttpOutputMessage streamingRequest =
					(StreamingHttpOutputMessage) request;
			streamingRequest.setBody(new StreamingHttpOutputMessage.Body() {
				@Override
				public void writeTo(OutputStream outputStream) throws IOException {
					StreamUtils.copy(body, outputStream);
					outputStream.flush();
					outputStream.close();
				}
			});
		}
		else {
			StreamUtils.copy(body, request.getBody());
		}

		request.execute();
		FileCopyUtils.copy(body, request.getBody());
	}
