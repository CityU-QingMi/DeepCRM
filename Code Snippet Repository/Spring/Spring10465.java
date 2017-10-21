	private MockResponse multipartRequest(RecordedRequest request) {
		MediaType mediaType = MediaType.parseMediaType(request.getHeader("Content-Type"));
		assertTrue(mediaType.isCompatibleWith(MediaType.MULTIPART_FORM_DATA));
		String boundary = mediaType.getParameter("boundary");
		Buffer body = request.getBody();
		try {
			assertPart(body, "form-data", boundary, "name 1", "text/plain", "value 1");
			assertPart(body, "form-data", boundary, "name 2", "text/plain", "value 2+1");
			assertPart(body, "form-data", boundary, "name 2", "text/plain", "value 2+2");
			assertFilePart(body, "form-data", boundary, "logo", "logo.jpg", "image/jpeg");
		}
		catch (EOFException ex) {
			throw new IllegalStateException(ex);
		}
		return new MockResponse().setResponseCode(200);
	}
