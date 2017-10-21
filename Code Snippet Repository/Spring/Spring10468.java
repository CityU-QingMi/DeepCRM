	private MockResponse patchRequest(RecordedRequest request, String expectedRequestContent,
			String contentType, byte[] responseBody) {

		assertEquals("PATCH", request.getMethod());
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
		Buffer buf = new Buffer();
		buf.write(responseBody);
		return new MockResponse().setResponseCode(201)
				.setHeader("Content-Length", responseBody.length)
				.setHeader("Content-Type", contentType)
				.setBody(buf);
	}
