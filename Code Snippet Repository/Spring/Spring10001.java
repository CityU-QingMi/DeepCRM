	@Test
	public void multipleWrites() throws Exception {
		AsyncClientHttpRequest request = this.factory.createAsyncRequest(new URI(baseUrl + "/echo"), HttpMethod.POST);
		final byte[] body = "Hello World".getBytes("UTF-8");

		if (request instanceof StreamingHttpOutputMessage) {
			StreamingHttpOutputMessage streamingRequest = (StreamingHttpOutputMessage) request;
			streamingRequest.setBody(outputStream -> StreamUtils.copy(body, outputStream));
		}
		else {
			StreamUtils.copy(body, request.getBody());
		}

		Future<ClientHttpResponse> futureResponse = request.executeAsync();
		ClientHttpResponse response = futureResponse.get();
		try {
			FileCopyUtils.copy(body, request.getBody());
			fail("IllegalStateException expected");
		}
		catch (IllegalStateException ex) {
			// expected
		}
		finally {
			response.close();
		}
	}
