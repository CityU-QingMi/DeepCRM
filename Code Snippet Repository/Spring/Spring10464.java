	private MockResponse jsonPostRequest(RecordedRequest request, String location, String contentType) {
		if (request.getBodySize() > 0) {
			assertTrue("Invalid request content-length",
					Integer.parseInt(request.getHeader("Content-Length")) > 0);
			assertNotNull("No content-type", request.getHeader("Content-Type"));
		}
		return new MockResponse()
				.setHeader("Location", baseUrl + location)
				.setHeader("Content-Type", contentType)
				.setHeader("Content-Length", request.getBody().size())
				.setBody(request.getBody())
				.setResponseCode(201);
	}
