	private MockResponse putRequest(RecordedRequest request, String expectedRequestContent) {
		assertTrue("Invalid request content-length",
				Integer.parseInt(request.getHeader("Content-Length")) > 0);
		String requestContentType = request.getHeader("Content-Type");
		assertNotNull("No content-type", requestContentType);
		Charset charset = StandardCharsets.ISO_8859_1;
		if (requestContentType.contains("charset=")) {
			String charsetName = requestContentType.split("charset=")[1];
			charset = Charset.forName(charsetName);
		}
		assertEquals("Invalid request body", expectedRequestContent, request.getBody().readString(charset));
		return new MockResponse().setResponseCode(202);
	}
