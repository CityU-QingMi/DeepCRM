	@Test
	public void headersAfterExecute() throws Exception {
		AsyncClientHttpRequest request = this.factory.createAsyncRequest(new URI(baseUrl + "/echo"), HttpMethod.POST);
		request.getHeaders().add("MyHeader", "value");
		byte[] body = "Hello World".getBytes("UTF-8");
		FileCopyUtils.copy(body, request.getBody());

		Future<ClientHttpResponse> futureResponse = request.executeAsync();
		ClientHttpResponse response = futureResponse.get();
		try {
			request.getHeaders().add("MyHeader", "value");
			fail("UnsupportedOperationException expected");
		}
		catch (UnsupportedOperationException ex) {
			// expected
		}
		finally {
			response.close();
		}
	}
