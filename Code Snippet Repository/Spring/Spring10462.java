	private MockResponse getRequest(RecordedRequest request, byte[] body, String contentType) {
		if (request.getMethod().equals("OPTIONS")) {
			return new MockResponse().setResponseCode(200).setHeader("Allow", "GET, OPTIONS, HEAD, TRACE");
		}
		Buffer buf = new Buffer();
		buf.write(body);
		MockResponse response = new MockResponse()
				.setHeader("Content-Length", body.length)
				.setBody(buf)
				.setResponseCode(200);
		if (contentType != null) {
			response = response.setHeader("Content-Type", contentType);
		}
		return response;
	}
